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
	private ListeConversations listeConv;
	private int localPort;
	
	public ChatViewController(User user) {

	  System.out.println("coucou");
  	  ChatView chat = new ChatView(user);
  	  this.comproces = new Communication(localPort, localUser);
  	  this.listeConv = new ListeConversations();
  	  final Conversation conversation = listeConv.getConversation(user); 
  	  chat.setListeners(this);
  	  this.localUser = user;
  	  SenderMessage SM = new SenderMessage(localUser);
  	  comproces.setSender(SM);
  	  chat.setConvo(conversation);
	}
	
	
	public void addReceivedMessage(Message message) {
		// TODO Auto-generated method stub
		
	}

	public void sendMessage(User contact, String data) {
		System.out.println("arrive la pour envoyer message : "+ data + " a l'user : " + contact.toString());
		this.comproces.getSenderMessage().sendMessage(contact, data);
		
	}

	public void addSentMessageToConversation(User contact, Message message) {
		// TODO Auto-generated method stub
		
	}
}
