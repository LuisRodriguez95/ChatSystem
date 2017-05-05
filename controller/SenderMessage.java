package controller;


import java.net.InetAddress;
import java.net.UnknownHostException;

import model.Message;
import model.User;
import tcp.ContactSocket;
import user.MessageUser.typeConnect;

public class SenderMessage {
	
	public SenderMessage(){
	}
	
	public void sendMessage(User contact,Message message) {
		//System.out.println("appele pour envoyer message : "+ data + " a l'user : " + contact.toString());
		ContactSocket sckt = new ContactSocket(contact.getIp(), contact.getPort());
		sckt.sendMessage(message);
	}
//
//	public static void main(String[] args) {
//		User localUser=null;
//		User remote=null;
//		try {
//			localUser = new User("Michel", InetAddress.getLocalHost(), 6001, 6002,typeConnect.CONNECTED);
//			remote = new User("Thibaut", InetAddress.getLocalHost(), 6000,6002, typeConnect.CONNECTED);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		SenderMessage sdr = new SenderMessage();
//		Message mess = new Message(localUser, "Salut bitch");
//		sdr.sendMessage(remote,mess );
//		
//	}
//		

}
