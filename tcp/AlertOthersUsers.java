package tcp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

import model.User;
import user.MessageUser;


public class AlertOthersUsers implements Runnable {
	private InetAddress group;
	private MulticastSocket socket;
	private int portMulticast;
	private Object objToSend;

	/**
	 * Given an object, send a multicast datagram to the given MulticastAddress
	 * @param multicastAddress Address of the multicast to connect with peer
	 * @param port Port you want to connect with
	 * @param objToSend the Object to send in multicat
	 */
	public AlertOthersUsers(String multicastAddress, int portMulticast,User localUser){
		try {
			this.group = InetAddress.getByName(multicastAddress);
		} catch (UnknownHostException e) {
			System.out.println("Erreur à la création du groupe multicast");
			e.printStackTrace();
		}
		try {
			this.socket= new MulticastSocket(portMulticast);
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
		this.objToSend = new MessageUser(localUser.getPseudo(), localUser.getIp(), localUser.getPort(), localUser.getEtat());
		this.portMulticast=portMulticast;
	}

	public void send(Object objToSend){   //fonction blocante pour le moment
		// First step : create an serializable object to send ( messageUser ) 
		//MessageUser me = new MessageUser("Joris",InetAddress.getByName("127.0.0.1"),2000,typeConnect.CONNECTED);
		// 2 Step : create ByteArrayOutputStream
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
		try {
			// 3 step : Construct objectOutputStream using the bytearray
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			os.flush();
			os.writeObject(objToSend);
			os.flush();				
			// 4 Step : retrieves byte array
			byte[] sendBuf = byteStream.toByteArray();
			DatagramPacket datagram = new DatagramPacket(sendBuf, sendBuf.length,this.group,this.portMulticast);
			this.socket.send(datagram);
			os.close();
		} catch (IOException e) {
			System.out.println("Peut venir de plusieurs endroit ci-dessus");
			e.printStackTrace();
		}
	}
	


public void run() {
	while(true){
		try {
			Thread.sleep(2000);
			this.send(this.objToSend);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}
