package model;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Conversation {  

	private DefaultListModel<Message> messageList = new DefaultListModel<Message>();
	
	public DefaultListModel<Message> getMessageList() {
		return messageList;
	}

	public Conversation(){ 
	}

	public void addMessage(Message message){
		messageList.addElement(message);
	}
	
	public String toString(){
		return this.messageList.toString();	
	}
	
}


