package model;

import interfaces.MessageChannel;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import user.MessageUser.typeConnect;

public class ListeConversations {
	
	private HashMap<User,Conversation> listeConversations = new HashMap<User,Conversation>();
	
	public ListeConversations(){
	}
	
	public Conversation getConversation(User user){
		if (this.listeConversations.get(user)!=null) {
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
	
	
	
//	
//	//TEST
//	public static void main(String[] args) {
//		User me=null;
//		User remote=null;
//		try {
//			me = new User("Thibaut",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
//			remote = new User("Michel",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//		ListeConversations liste = new ListeConversations();
//		
//		Message messtoSend = new Message(me,"Salut comment tu vas  ? ");
//		Message recvmess = new Message(remote,"la baiss et toi? ? ");
////		Message recvMess1 = new Message(bastien,"Yo negrito, tu peux me filer le cv stp ");
////		Message reponse = new Message(me,"yes, sure ");
//		liste.addSentMessageToConversation(remote,messtoSend);
//		//liste.addSentMessageToConversation(remote,messtoSend);
//		liste.addReceivedMessage(recvmess);
////		liste.addReceivedMessage(recvMess1);
////		liste.addSentMessageToConversation(bastien, reponse);
//		System.out.println(liste.toString());
//	}


}
