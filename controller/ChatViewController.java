package controller;

import interfaces.MessageChannel;
import model.Conversation;
import model.ListeConversations;
import model.Message;
import model.User;
import view.ChatView;

public class ChatViewController implements MessageChannel{

	private Communication comproces;
	public  User localUser; // localUser is the user connected to the ChatSystem 
	
	public ChatViewController(User user) {

	  System.out.println("coucou");
  	  ChatView chat = new ChatView(user);
  	  ListeConversations listeConv = new ListeConversations();
  	  final Conversation conversation = listeConv.getConversation(user); 
  	  chat.setListeners(this);
  	  this.localUser = user;
  	  chat.setConvo(conversation);
	}
	
	
	public void addReceivedMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	public void sendMessage(User contact, String data) {
		this.comproces.getSenderMessage().sendMessage(contact, data);
		
	}

	public void addSentMessageToConversation(User contact, Message message) {
		// TODO Auto-generated method stub
		
	}
}
