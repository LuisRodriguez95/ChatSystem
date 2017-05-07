package controller;
import java.net.InetAddress;

import tcp.AlertOthersUsers;
import tcp.CheckConnectedUsers;

import communication.User;
import model.ConnectedUsers;

public class UserListController  {
	private final User localUser; // localUser is the user connected to the ChatSystem 
	private final String adrMulticast="228.5.6.7";  // TO MODIFY IF NEEDED
	private final InetAddress ipMulticast; 
	private final int portMulticast=6789;
	private final UpdateConnectedUsers updater;
	private final AlertOthersUsers alerter;
	private final ConnectedUsers users;
	private final CheckConnectedUsers listeningSocket;
	
public UserListController(User localUser) {
		this.localUser=localUser;
		InetAddress ipMultiInterm=null;
		try {
			ipMultiInterm =  InetAddress.getByName(this.adrMulticast);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.users = new ConnectedUsers();
		this.ipMulticast=ipMultiInterm;
		this.updater = new UpdateConnectedUsers(users);
		this.alerter = new AlertOthersUsers(this.ipMulticast, this.portMulticast, this.localUser);
		this.listeningSocket = new CheckConnectedUsers(this.ipMulticast, this.portMulticast, this.localUser);
		this.listeningSocket.setListener(updater);  // Le controlleur de mise à jour des users écoute le serveur d'écoute de datagram
	}
	
	public void startChatProcess() {
		alerter.startAlerter();
		listeningSocket.startChecker();
		updater.startDetection();
	}
	
	public ConnectedUsers getUsers(){
		return this.users;
	}
	
	public void sendDisconnexion(){
		this.alerter.disconnect(localUser);
	}
	
	
//	public static void main(String[] args) {
//		User me=null;
//		try {
//			me = new User("Denis", InetAddress.getLocalHost(), 6000,6002, typeConnect.CONNECTED);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		UserListController ctrl = new UserListController(me);
//		ctrl.startChatProcess();
//		new ConnectedUsersWindow(me);
//
//	}
	
}
	

