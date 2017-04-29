package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import interfaces.CanalVuesController;
import model.ConnectedUsers;
import model.ListeConversations;
import model.User;
import user.MessageUser.typeConnect;
import view.ConnectWindow;

public class Controller implements CanalVuesController{
	private User localUser;
	private  InetAddress ipContact; // FINAL ! 
	private final int portContact;
	//private final ListeConversations convos;
	public Controller(){
		try {
			this.ipContact = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.portContact = 6000; 		//rajouter la randomization de luis qui se situe en bas de la page
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
		//demarrer la communication egalement
		//afficher la vue de communication
		while (true) {  // To be removed... juste un test 
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(ConnectedUsers.getInstance().toString());
		}
	}

	public void setLocalUser(String pseudo) {
		this.localUser= new User(pseudo,this.ipContact,this.portContact, typeConnect.CONNECTED);
	}

	public static void main(String[] args) {
		Controller ct = new Controller();
		ct.run();
	}
}





//public void startChatProcess(String pseudo) {
////	ConnectedUsersWindow window = new ConnectedUsersWindow();
////	UserListListener listener = this;
////	window.setListener(listener);
//	System.out.println("Welcome " + pseudo);
//	InetAddress ip;
//	try {
//		ip = InetAddress.getLocalHost();
//		Random r = new Random();
//		portContact = r.nextInt(upperbound-lowerbound) + lowerbound;
//		this.localUser= new User(pseudo, ip, portContact, typeConnect.CONNECTED); //LocalUser
//	} catch (UnknownHostException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	
//	CheckConnectedUsers listeningSocket = new CheckConnectedUsers("228.5.6.7", this.portBroadcast); 
//	listeningSocket.setLocalUser(this.localUser);
//	
//	AlertOthersUsers alerter = new AlertOthersUsers("228.5.6.7",  this.portBroadcast , this.localUser);
//	new Thread(alerter).start();
//	new Thread(listeningSocket).start();
//}

