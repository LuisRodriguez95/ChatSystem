package controller;

import interfaces.MessageChannel;

import java.io.FileNotFoundException;

import model.ListeConversations;
import model.Message;
import tcp.FileClient;
import tcp.FileServer;
import tcp.TCPServer;

import communication.User;

public class Communication implements MessageChannel { //echangerMessages
	private TCPServer server;
	private SenderMessage sender;
	private final int localPort;
	private final ListeConversations convos;
	private final communication.User localUser;
	private final FileServer fileServer;
	
	public Communication(communication.User localUser2){
		this.localUser=localUser2;
		this.sender = new SenderMessage();
		this.localPort=localUser2.getPort();
		this.convos = new ListeConversations();
		this.fileServer = new FileServer(9002);
	}

	public void startServer(){ //jamais appele
		this.server = new TCPServer(this.localPort);
		server.setListener(this);
		new Thread(this.server).start();
		this.fileServer.startServer();
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
	
	public void sendFile(User contact){
		String filepath="";
		FileBrowser fBrowser= new FileBrowser();
		try {
			filepath=fBrowser.getFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File sended to "+ contact.getPseudo() + ":" + filepath);
		FileClient fClient = new FileClient(contact.getIP(), 8000);
		fClient.sendfile(filepath);
	}
//
//	public static void main(String[] args) {
//		User localUser=null;
//		try {
//			localUser = new User("Michel", InetAddress.getLocalHost(), 6001, 6002,typeConnect.CONNECTED);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//
//		Communication com = new Communication(localUser);
//		com.startServer();
//
//		while(true){
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println(com.getConvos().toString());
//		}
//	}
}
