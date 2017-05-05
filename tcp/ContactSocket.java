package tcp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import communication.Message;


public class ContactSocket implements Runnable{
	private Socket senderSocket;
	private final InetAddress address;
	private final int contactPort;
	private Message messageToSend;
	
	public ContactSocket(InetAddress address,int contactPort){
		this.address=address;
		this.contactPort=contactPort;
		try{
			this.senderSocket = new Socket(address, contactPort);
		}
		catch (IOException e){
			System.out.println("Erreur à la création du socket client \n");
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message message) {
		this.messageToSend=message;
		new Thread(this).start();
	}

	public void run() {
		ObjectOutputStream objectOutput ;
		try {
			objectOutput = new ObjectOutputStream(senderSocket.getOutputStream());
			objectOutput.writeObject(this.messageToSend);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try {
			this.senderSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Message envoyé : (ad " + this.address +"port : "+this.contactPort + this.messageToSend.toString());
	}
//	
//	public static void main(String[] args) {
//		User me=null;
//		try {
//			me = new User("Thibaut",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Message messtoSend = new Message(me,"Salut comment tu vas  ? ");
//		InetAddress adres = null;
//		try {
//			adres = InetAddress.getLocalHost();
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ContactSocket sender = new ContactSocket(adres, 6400);
//		sender.sendMessage(messtoSend);
//		System.out.println("Message envoyé");
//	}

}
