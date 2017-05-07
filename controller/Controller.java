package controller;

import interfaces.CanalVuesController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import view.ChatView;
import view.ConnectWindow;
import view.ConnectedUsersWindow;

import communication.User;
import communication.User.typeConnect;

public class Controller implements CanalVuesController{
	private User localUser;
	private  InetAddress ipContact; // FINAL ! 
	private final int portContact;
	private final int portFile;
	private Communication comProcess;
	private UserListController userListctrl;
	
	public Controller(){
		try {
			this.ipContact = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}	
		Random r = new Random();
		this.portContact = r.nextInt(30000-6000) + 6000;
		this.portFile = r.nextInt(30000-6000) + 6000;
	}
	
	/**
	 * Premiere methode appelée par le main 
	 */
	public void run(){
		final ConnectWindow view = new ConnectWindow();
		view.setConnectListener(this);
	}

	public void startChat() {
		userListctrl = new UserListController(this.localUser);
		userListctrl.startChatProcess();
		this.comProcess = new Communication(this.localUser);
		this.comProcess.startServer();
		
		ConnectedUsersWindow cUW = new ConnectedUsersWindow(localUser,userListctrl.getUsers());
		cUW.setListener(this); 
		this.comProcess.setListener(cUW);
	}

	public void setLocalUser(String pseudo) {
		this.localUser= new User(pseudo,this.ipContact,this.portContact, typeConnect.CONNECTED);
		this.localUser.setPortFile(portFile);
	}

	public void setLocalStatut(String statut) {
		this.localUser.setStatut(statut);
	}
	
	public static void main(String[] args) {
		Controller ct = new Controller();
		ct.run();
	}

	public void openChatView(User user) {
		ChatView chat = new ChatView(user);
		chat.setConvo(comProcess.getConvos().getConversation(user));
		chat.setListeners(comProcess);
		chat.updatechatView();
	}

	public void disconnect() {
		userListctrl.sendDisconnexion();
		
	}

	
}