package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observer;

import model.Conversation;
import model.User;

import user.MessageUser.typeConnect;
import view.ChatView;
import view.ConnectListener;
import view.ConnectWindow;

public class mainage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
 
		final ChatView view = new ChatView();
		 
		view.createFrame("Luis");
		int i = 0;
		while(i<3){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (i%2==0){
				User user;
				try {
					user = new User("Luis",InetAddress.getLocalHost() , 1223, typeConnect.CONNECTED);
					Conversation.addMessage(user, "pairsqdfqsdfsqd \n qsdfsqdfsqdf",new Date());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				User user;
				try {
					user = new User("Tibo",InetAddress.getLocalHost() , 1223, typeConnect.CONNECTED);
					Conversation.addMessage(user, "imapir",new Date());
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
/*
		final ConnectWindow view = new ConnectWindow();
		final ConnectListener ct = new ChatProcess();
		view.setConnectListener(ct);
*/
	}

}
