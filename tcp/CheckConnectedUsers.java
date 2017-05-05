package tcp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import model.ConnectedUsers;
import user.MessageUser;
import model.User;
import controller.UpdateConnectedUsers;


public class CheckConnectedUsers implements Runnable{
	private final InetAddress group;
	private MulticastSocket socket;
	private final int port;
	private final User localUser ; 

	/**
	 * Reçoit en permanence les datagrams envoyés à destination du groupe multicast (messages de type MessageUser)
	 * @param multicastAdress Address of the multicast to connect with peer
	 * @param port Port you want to connect with
	 */
	public CheckConnectedUsers(InetAddress multicastAdress, int port, User localUser){
		this.port=port;
		this.group = multicastAdress;
		try {
			this.socket= new MulticastSocket(this.port);
		} catch (IOException e) {
			System.out.println("Erreur à la création du socket pour multicast");
			e.printStackTrace();
		}
		try {
			socket.joinGroup(group);
		} catch (IOException e) {
			System.out.println("Erreur au bind du socket multicast");
			e.printStackTrace();
		}
		this.localUser=localUser;
	}
	

	public void recv(){
		byte[] recvBuf = new byte[1000];
		DatagramPacket packet = new DatagramPacket(recvBuf,recvBuf.length);
		try {
			this.socket.receive(packet);
			ByteArrayInputStream byteStream = new ByteArrayInputStream(recvBuf);
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
			try {  //receivedObject
				Object recvUser = is.readObject();
				//System.out.println("New User : " + recvUser.toString());
				updateModel(recvUser);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/** given the recvObject, update the model ( The connected Users List ) using
	 * @param recvObject of type MessageUser
	 */
	public void updateModel(Object recvObject){
		MessageUser msgUser = (MessageUser)recvObject;// Mieux vaudrait faire avec le hashcode de l'objet
		User user = new User(msgUser.getPseudo(), msgUser.getIP(), msgUser.getPort(),msgUser.getEtat());
		if (!user.equals(this.localUser) ){
			if (!ConnectedUsers.getInstance().contains(user)){ // Si l'utiliasteur est nouveau
				UpdateConnectedUsers.getInstance().updateUser(user);
			}
			else{	// S'il existe deja on met a jour sa date
				User theUser = ConnectedUsers.getInstance().getUser(user);
				UpdateConnectedUsers.getInstance().updateDate(theUser);
			}
		}
	}
		

	public void run() {
		while(true){
			this.recv();
		}
	}
}
