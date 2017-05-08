package model;


import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import communication.User;

/**
 *  This class allows a representation of all the connected users according to the MessagesUsers received from the CheckConnectedUsers socket <br>
 *  This class implements the Singleton design pattern, ( we just need one list of connected user for an application ) 
 * @author chivunito
 */
public class ConnectedUsers extends DefaultListModel<User> implements ListSelectionListener {  

	
	public ConnectedUsers(){  
		super();
	}
		
	public boolean containsUser(User user){
		return this.contains(user);
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
	}

}
