package view;

import java.util.Observer;

public interface ConnectListener {
	public void startChatProcess(String pseudo);
	public void sendMessage(String message);
	
}
