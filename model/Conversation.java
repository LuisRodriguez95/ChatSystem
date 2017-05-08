package model;

import javax.swing.DefaultListModel;

import communication.Message;


public class Conversation extends DefaultListModel<Message> {  

	public Conversation(){ 
		super();
	}

	public void addMessage(Message message){
		this.addElement(message);
	}
	
}


