package controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import interfaces.MessageChannel;
import model.Conversation;
import model.ListeConversations;
import model.Message;
import model.User;
import user.MessageUser.typeConnect;
import view.ChatView;

public class ChatViewController {

	private final User localUser;
	// private final ListeConversations listeConv;

	public ChatViewController(User user) {
		this.localUser = user;
	}

	public static void main(String[] args) {
		User localUser=null;
		User remote=null;
		try {
			localUser = new User("Thibaut", InetAddress.getLocalHost(), 6000, typeConnect.CONNECTED);
			remote = new User("Michel", InetAddress.getLocalHost(), 6001, typeConnect.CONNECTED);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Communication com = new Communication(localUser);
		com.startServer();
		ChatView chat = new ChatView(remote);
		ListeConversations lste= new ListeConversations();
		chat.setConvo(lste.getConversation(remote));
		chat.setListeners(com);
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(com.getConvos());
		}
	}


}
