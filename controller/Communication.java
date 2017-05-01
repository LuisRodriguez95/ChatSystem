package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import interfaces.MessageChannel;
import model.ListeConversations;
import model.Message;
import model.User;
import sun.applet.Main;
import tcp.TCPServer;
import user.MessageUser.typeConnect;

public class Communication implements MessageChannel { //echangerMessages
	private TCPServer server;
	private SenderMessage sender;
	private final int localPort;
	private final ListeConversations convos;
	private final User localUser;

	public Communication(User localUser){
		this.localUser=localUser;
		this.sender = new SenderMessage();
		this.localPort=localUser.getPort();
		this.convos = new ListeConversations();
	}

	public void startServer(){ //jamais appele
		this.server = new TCPServer(this.localPort);
		server.setListener(this);
		new Thread(this.server).start();
	}

	public ListeConversations getConvos(){
		return this.convos;
	}

	public void addReceivedMessage(Message message) {
		this.convos.addReceivedMessage(message);		
	}

	public void sendMessage(User contact, String data) {
		Message sms = new Message(this.localUser, data);
		this.sender.sendMessage(contact,sms);
		this.convos.addSentMessageToConversation(contact,sms);
	}

	public static void main(String[] args) {
		User localUser=null;
		try {
			localUser = new User("Michel", InetAddress.getLocalHost(), 6001, typeConnect.CONNECTED);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		Communication com = new Communication(localUser);
		com.startServer();

		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(com.getConvos().toString());
		}
	}
}
