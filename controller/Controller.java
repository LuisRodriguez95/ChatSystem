package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import interfaces.CanalVuesController;
import model.ConnectedUsers;
import model.ListeConversations;
import model.User;
import user.MessageUser.typeConnect;
import view.ChatView;
import view.ConnectWindow;
import view.ConnectedUsersWindow;

public class Controller implements CanalVuesController{
	private User localUser;
	private  InetAddress ipContact; // FINAL ! 
	private final int portContact;
	private Communication comProcess;
	
	public Controller(){
		try {
			this.ipContact = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Random r = new Random();
		this.portContact = r.nextInt(30000-6000) + 6000;
	}
	
	/**
	 * Premiere methode appelée par le main 
	 */
	public void run(){
		final ConnectWindow view = new ConnectWindow();
		view.setConnectListener(this);
	}

	public void startChat() {
		UserListController userListctrl = new UserListController(this.localUser);
		userListctrl.startChatProcess();
		this.comProcess = new Communication(this.localUser);
		this.comProcess.startServer();
		ConnectedUsersWindow cUW = new ConnectedUsersWindow();
		cUW.setListener(this);  
	}

	public void setLocalUser(String pseudo) {
		this.localUser= new User(pseudo,this.ipContact,this.portContact, typeConnect.CONNECTED);
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
		chat.setConvo(this.comProcess.getConvos().getConversation(user));
		chat.setListeners(comProcess);
		// il ne faut pas l'ouvrir à chaque fois, il faut la get...
	}
	
}