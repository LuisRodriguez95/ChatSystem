package model;

import java.util.HashMap;
import java.util.Map;

import communication.Message;
import communication.User;

public class ListeConversations extends HashMap<User,Conversation>{

	public ListeConversations(){
		super();
	}

	public Conversation getConversation(User user){
		if (contains(user)) {
			return this.get(user);
		}
		else{
			Conversation conv = new Conversation(); 
			this.put(user, conv);
			return this.get(user);	

		}
	}
	
	public boolean contains(User user){
		if (get(user)!=null){
			return true;
		}
		else {
			return false;
		}
	}
	

	public void addReceivedMessage(Message message) {
		Conversation conv = this.getConversation(message.getSender());
		conv.addMessage(message);

	}

	public void addSentMessageToConversation(User contact,Message message){
		Conversation conv = this.getConversation(contact);
		conv.addMessage(message);
	}

	public String toString(){
		String retour="Liste des Conversations : \n";
		for(Map.Entry<User, Conversation> entry : this.entrySet()){
			retour += "Contact : " + entry.getKey().getPseudo() + ": \n" + entry.getValue().toString() +"\n";
		}
		return retour;
	}
}