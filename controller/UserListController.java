package controller;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import interfaces.ConnectListener;
import interfaces.UserListListener;
import model.User;
import tcp.AlertOthersUsers;
import tcp.CheckConnectedUsers;
import user.MessageUser.typeConnect;
import view.ConnectedUsersWindow;

public class UserListController implements ConnectListener, UserListListener {
	public  User localUser; // localUser is the user connected to the ChatSystem 
	private int portContact=3400; // his contact port 
// rajouter private int adresseIPdeBroadcast
	private int upperbound = 30000;
	private int lowerbound = 2000;
	private int portBroadcast=6789;
	/**
	 * 
	 * @param pseudo of the user connected to the ChatSystem
	 */
	

	public UserListController() {
	}
	
	public void startChatProcess(String pseudo) {
		ConnectedUsersWindow window = new ConnectedUsersWindow();
		UserListListener listener = this;
		window.setListener(listener);
		System.out.println("Welcome " + pseudo);
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			Random r = new Random();
			portContact = r.nextInt(upperbound-lowerbound) + lowerbound;
			this.localUser= new User(pseudo, ip, portContact, typeConnect.CONNECTED); //LocalUser
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CheckConnectedUsers listeningSocket = new CheckConnectedUsers("228.5.6.7", this.portBroadcast); 
		listeningSocket.setLocalUser(this.localUser);
		
		AlertOthersUsers alerter = new AlertOthersUsers("228.5.6.7",  this.portBroadcast , this.localUser);
		new Thread(alerter).start();
		new Thread(listeningSocket).start();
	}
	
	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		
	}

	public void openChat(User user) {
	  	  new ChatViewController(localUser);
		
	}

	public void setLUPseudo(String pseudo) {
		this.localUser.setPseudo(pseudo);
		
	}
	
	

}
