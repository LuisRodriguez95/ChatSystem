package interfaces;

import communication.User;

//Allow the Communication class to alert the view of a new message to read
public interface AlerterNewMessage {
	public void newMessage(User user);
}
