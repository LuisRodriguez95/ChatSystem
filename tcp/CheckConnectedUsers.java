package tcp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


import model.ConnectedUsers;

import communication.User;
import communication.User.typeConnect;
import interfaces.UserUpdater;


public class CheckConnectedUsers implements Runnable{
	private final InetAddress group;
	private MulticastSocket socket;
	private final int port;
	private final User localUser ; 
	private UserUpdater listener;
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
//				System.out.println("New User : " + recvUser.toString());
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
		User user = (User) recvObject;
		if (!user.equals(this.localUser) ){
			listener.updateUser(user);
		}
	}
	
	public void startChecker(){
		new Thread(this).start();
	}

	public void setListener(UserUpdater listener) {
		this.listener = listener;
	}
	
	public void run() {
		while(true){
			this.recv();
		}
	}
	public static void main(String[] args) {
		InetAddress ip = null;
		try {
			ip=InetAddress.getByName("228.5.6.7");
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user1= new User("Denis", ip, 8000, typeConnect.CONNECTED);
		CheckConnectedUsers checkConnectedUsers = new CheckConnectedUsers(ip, 6000,user1);
		checkConnectedUsers.startChecker();
		while(true){
			System.out.println(ConnectedUsers.getInstance().getListUsers().toString());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
