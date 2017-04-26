package controller;
import java.net.InetAddress;
import java.net.UnknownHostException;

import model.User;
import tcp.AlertOthersUsers;
import tcp.CheckConnectedUsers;
import user.MessageUser.typeConnect;
import view.ConnectListener;

public class ChatProcess implements ConnectListener {
	public static User localUser; // localUser is the user connected to the ChatSystem 
	private int portContact=3400; // his contact port 
// rajouter private int adresseIPdeBroadcast
	private int portBroadcast=6789;
	/**
	 * 
	 * @param pseudo of the user connected to the ChatSystem
	 */
	

	public ChatProcess() {
	}
	
	public void startChatProcess(String pseudo) {
		System.out.println("Welcome " + pseudo);
		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
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
	

}
