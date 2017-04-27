package model;

import java.net.InetAddress;
import java.net.UnknownHostException;

import user.MessageUser;
import user.MessageUser.typeConnect;

public class Local {

	private static Local instance=new Local(); 
	private static User user;

	private Local(){
	}
	
	public static void setUserInfo(String pseudo, int port){
		try {
			InetAddress ip = InetAddress.getLocalHost();
			user= new User(pseudo, ip, port, typeConnect.CONNECTED);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static User getUser(){
		return Local.user;
	}
	

	
}
