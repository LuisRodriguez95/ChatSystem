package controller;

import interfaces.AlerterNewMessage;
import interfaces.MessageChannel;

import java.io.FileNotFoundException;

import model.ListeConversations;
import tcp.FileClient;
import tcp.FileServer;
import tcp.TCPServer;
import communication.Message;
import communication.User;

public class Communication implements MessageChannel { //echangerMessages
	private TCPServer server;
	private SenderMessage sender;
	private final int localPort;
	private final ListeConversations convos;
	private final communication.User localUser;
	private final FileServer fileServer;
	private AlerterNewMessage listener;
	public Communication(communication.User localUser){
		this.localUser=localUser;
		this.sender = new SenderMessage();
		this.localPort=localUser.getPort();
		this.convos = new ListeConversations();
		this.fileServer = new FileServer(this.localUser.getPortFile());
	}

	public void startServer(){ 
		this.server = new TCPServer(this.localPort);
		server.setListener(this);
		new Thread(this.server).start();
		this.fileServer.startServer();
	}

	public ListeConversations getConvos(){
		return this.convos;
	}

	synchronized public void addReceivedMessage(Message message) {
		this.convos.addReceivedMessage(message);
		this.listener.newMessage(message.getSender());
	}

	public void sendMessage(User contact, String data) {
		Message sms = new Message(data,this.localUser);
		this.sender.sendMessage(contact,sms);
		this.convos.addSentMessageToConversation(contact,sms);
	}
	
	public void setListener(AlerterNewMessage listener){
		this.listener=listener;
	}
	
	public void sendFile(User contact){
		String filepath="";
		FileBrowser fBrowser= new FileBrowser();
		try {
			filepath=fBrowser.getFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FileClient fClient = new FileClient(contact.getIP(), contact.getPortFile());
		fClient.sendfile(filepath);
	}


}
