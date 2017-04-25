package model;

import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Conversation implements ListSelectionListener {  

	
	private static DefaultListModel<UserMessage> listUserMessage = new DefaultListModel<UserMessage>();
	
	private static Conversation instance = new Conversation();   
	
	public static Conversation getInstance()
	{	
		return instance;
	}
	
	private Conversation(){   //Private constructor to implement Singleton design pattern
	}
	
	public DefaultListModel<UserMessage> getListUserMessage() {
		return listUserMessage;
	}
	
	public static void addMessage(User user, String message,Date date){
		UserMessage mess = new UserMessage();
		mess.setMessage(message);
		mess.setUser(user);
		mess.setDate(date);
		listUserMessage.addElement(mess);
		System.out.println("message " + message + " succesfully added to the conversation");
	}
	
	public String toString(){
		return this.listUserMessage.toString();
		
	}
	public void valueChanged(ListSelectionEvent arg0) {
		System.out.println("cha");
	}
	

}
