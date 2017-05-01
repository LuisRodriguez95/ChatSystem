package interfaces;

import model.User;

public interface CanalVuesController {
	public void startChat();
	public void setLocalUser(String pseudo);	
	public void setLocalStatut(String statut);
	public void openChatView(User user);
}
