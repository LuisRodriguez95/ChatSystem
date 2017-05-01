package controller;
import java.net.InetAddress;

import model.User;
import tcp.AlertOthersUsers;
import tcp.CheckConnectedUsers;
import user.MessageUser.typeConnect;
import view.ConnectedUsersWindow;

public class UserListController  {
	private final User localUser; // localUser is the user connected to the ChatSystem 
	private final String adrMulticast="228.5.6.7";  // TO MODIFY
	private final InetAddress ipMulticast; // adresse de contact 
	private final int portMulticast=6789;
	
	private final UpdateConnectedUsers updater;
	private final AlertOthersUsers alerter;
	private final CheckConnectedUsers listeningSocket;
//	private int upperbound = 30000;
//	private int lowerbound = 2000;
	
public UserListController(User localUser) {
		this.localUser=localUser;
		InetAddress ipMultiInterm=null;
		try {
			ipMultiInterm =  InetAddress.getByName(this.adrMulticast);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.ipMulticast=ipMultiInterm;
		this.updater = UpdateConnectedUsers.getInstance();
		this.alerter = new AlertOthersUsers(this.ipMulticast, this.portMulticast, this.localUser);
		this.listeningSocket = new CheckConnectedUsers(this.ipMulticast, this.portMulticast, this.localUser);
	}
	
	public void startChatProcess() {
		new Thread(alerter).start();
		new Thread(listeningSocket).start();
	}
	
	
	/**
	 * Methode permettant de tester le bon fonctionnement de cette classe, qui permet de 
	 *  - Savoir quels utilisteurs sont connectés
	 *  - Alerter les autres utilisateurs qui sont connectés
	 * @param args
	 */
	public static void main(String[] args) {
		User me=null;
		try {
			me = new User("Denis", InetAddress.getLocalHost(), 6000, typeConnect.CONNECTED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserListController ctrl = new UserListController(me);
		ctrl.startChatProcess();
		new ConnectedUsersWindow(me);

	}
	
}
	

