package model;

import java.util.HashMap;

import communication.User;

public class CompteurMessage {
	private HashMap<User, Integer> messages = new HashMap<User, Integer>();
	public CompteurMessage() {
	}
	
	public boolean getConversation(User user){
		return this.messages.containsKey(user);
	}
	
	public void addConv(User user){
		this.messages.put(user,0);
	}
	
	public void readConv(User user){
		this.messages.put(user,0);
	}
	public void addMess(User user){
		this.messages.put(user, this.messages.get(user) + 1);
	}
	
	
	public static void main(String[] args) {
		new CompteurMessage();
	}
}
