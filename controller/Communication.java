package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import model.ListeConversations;
import model.User;
import tcp.TCPServer;
import user.MessageUser.typeConnect;

public class Communication { //echangerMessages
	private TCPServer server;
	private final SenderMessage sender;
	private final int localPort;
	private final ListeConversations convos;
	private final User localUser;

	public Communication(int localPort, User localUser){
		this.localUser=localUser;
		this.localPort=localPort;
		this.convos = new ListeConversations();
		this.sender = new SenderMessage(localUser);
	}

	public SenderMessage getSenderMessage(){
		return this.sender;
	}
	public void startServer(){
		this.server = new TCPServer(this.localPort);
		server.setListener(convos);
		new Thread(this.server).start();
	}

	public ListeConversations getConvos(){
		return this.convos;
	}

	public static void main(String[] args) {
		if (args[0].contentEquals("Server")){		
			User localUser=null;
			try {
				localUser = new User("Thibaut", InetAddress.getLocalHost(), 6000, typeConnect.CONNECTED);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			Communication comprocess = new Communication(6000,localUser );
			comprocess.startServer();
			System.out.println("Démarrage du Process de communication : le serveur attends des messages ");
			while(true){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(comprocess.getConvos().toString());
			}
		}
		else{
			System.out.println("Client Side");
			User localUser=null;
			User remote=null;
			try {
				remote = new User("Thibaut", InetAddress.getLocalHost(), 6000, typeConnect.CONNECTED);
				localUser = new User("Remote", InetAddress.getLocalHost(), 6000, typeConnect.CONNECTED);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			Communication comprocess = new Communication(6000,localUser );
			comprocess.sender.sendMessage(remote, "Tu me reçois ?  ça va ? ");
		}

	}
}

