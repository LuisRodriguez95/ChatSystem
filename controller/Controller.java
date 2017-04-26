package controller;

import interfaces.ChangeView;
import interfaces.MessageChannel;
import model.Message;
import model.User;
import view.ChatView;

public class Controller implements ChangeView ,MessageChannel{
	private Communication comproces;
	
	public void openChat(User user){
  	  ChatView chat = new ChatView();
  	  chat.addlistener(this);
    }

	public void openConnectedUsersWindows() {
		// TODO Auto-generated method stub
		
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
