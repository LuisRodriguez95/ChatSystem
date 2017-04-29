package controller;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Message;
import model.User;
import tcp.ContactSocket;
import user.MessageUser.typeConnect;

public class SenderMessage {
	private final User localUser;
	
	public SenderMessage(User localUser){
		this.localUser=localUser;
	}
	
	public void sendMessage(User contact, String data) {
		System.out.println("appele pour envoyer message : "+ data + " a l'user : " + contact.toString());
		ContactSocket sckt = new ContactSocket(contact.getIp(), contact.getPort());
		Message messageToSend = new Message(this.localUser,data );
		sckt.sendMessage(messageToSend);
	}
	
	public void addReceivedMessage(Message message) {
		// TODO Auto-generated method stub
	}

	public void addSentMessageToConversation(User contact, Message message) {
		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		User localUser=null;
		User remote=null;
		try {
			localUser = new User("Denis", InetAddress.getLocalHost(), 6000, typeConnect.CONNECTED);
			remote = new User("Remote", InetAddress.getLocalHost(), 6001, typeConnect.CONNECTED);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SenderMessage sdr = new SenderMessage(localUser);
		sdr.sendMessage(remote, "Une salope");
		
	}
		

}
