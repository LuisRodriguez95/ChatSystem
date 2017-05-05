package interfaces;

import communication.Message;
import communication.User;

public interface MessageChannel {
	public void addReceivedMessage(Message message); 
	//Quand le CanalReception reçoit un message, il met à jour la liste des conversations en la rajoutant à la ListeConversations
	
	public void sendMessage(User contact, String data);
	//Quand l'utilisateur veut envoyer un message à un contact, il passe par cette interface
	
	public void sendFile(User contact);
}
