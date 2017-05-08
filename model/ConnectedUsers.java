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
public class ConnectedUsers implements ListSelectionListener {  

	/**
     * Set of users ( the hashCode is on the User's pseudo )
     */
	
	private DefaultListModel<User> listUsers = new DefaultListModel<User>();
	
	public ConnectedUsers(){   
	}
		
	public boolean containsUser(User user){
		return this.listUsers.contains(user);
	}
	
	
	public DefaultListModel<User> getListUsers() {
		return listUsers;
	}
	
	
	public void valueChanged(ListSelectionEvent arg0) {
	}

}
