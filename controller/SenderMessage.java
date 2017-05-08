package controller;


import tcp.ContactSocket;
import communication.Message;
import communication.User;

public class SenderMessage {
	
	public SenderMessage(){
	}
	
	public void sendMessage(User contact,Message message) {
		ContactSocket sckt = new ContactSocket(contact.getIP(), contact.getPort());
		sckt.sendMessage(message);
	}
	

}
