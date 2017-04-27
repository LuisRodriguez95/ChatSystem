package model;

import interfaces.MessageChannel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import user.MessageUser.typeConnect;

public class ListeConversations implements MessageChannel {
	
	private HashMap<User,Conversation> listeConversations = new HashMap<User,Conversation>();
	
	public ListeConversations(){
	}
	
	public Conversation getConversation(User user){
		if (this.listeConversations.containsValue(user)) {
			return this.listeConversations.get(user);
		}
		else{
			Conversation conv = new Conversation(); // creer une nouvelle conversation pour l'utilisateur
			this.listeConversations.put(user, conv);
			return this.listeConversations.get(user);			
		}
	}
	
	public void addReceivedMessage(Message message) {
		Conversation conv = this.getConversation(message.getExpediteur());
		if (conv!=null){
			conv.addMessage(message);
		}
		else{
			conv = new Conversation();
			conv.addMessage(message);
			this.listeConversations.put(message.getExpediteur(), conv);
		}		
	}
		
	public void addSentMessageToConversation(User contact,Message message){
		Conversation conv = this.getConversation(contact);
		if (conv!=null){
			conv.addMessage(message);
		}
		else{
			conv = new Conversation();
			conv.addMessage(message);
			this.listeConversations.put(contact, conv);
		}		
	}
	
	public String toString(){
		String retour="Liste des Conversations : \n";
		for(Map.Entry<User, Conversation> entry : this.listeConversations.entrySet()){
			retour += "Contact : " + entry.getKey().getPseudo() + ": \n" + entry.getValue().toString() +"\n";
		}
		return retour;
	}
	
	
	
	
	//TEST
	public static void main(String[] args) {
		User denis=null;
		User me=null;
		User bastien = null;
		try {
			me = new User("Thibaut",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
			denis = new User("Denis",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
			bastien = new User("bastien",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListeConversations liste = new ListeConversations();
		
		Message messtoSend = new Message(me,"Salut comment tu vas  ? ");
		Message recvmess = new Message(denis,"la baiss et toi? ? ");
		Message recvMess1 = new Message(bastien,"Yo negrito, tu peux me filer le cv stp ");
		Message reponse = new Message(me,"yes, sure ");
		liste.addSentMessageToConversation(denis,messtoSend);
		liste.addReceivedMessage(recvmess);
		liste.addReceivedMessage(recvMess1);
		liste.addSentMessageToConversation(bastien, reponse);
		System.out.println(liste.toString());
	}

	
	public void sendMessage(User contact, String data) {
		// TODO Auto-generated method stub
		
	}

}
