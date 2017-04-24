package model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	
	private static ConnectedUsers instance = new ConnectedUsers();   
	public static ConnectedUsers getInstance()
	{	return instance;
	}
	private ConnectedUsers(){   //Private constructor to implement Singleton design pattern
	}
	
	public DefaultListModel<User> getListUsers() {
		return listUsers;
	}
	
	public String toString(){
		return this.listUsers.toString();
		
//		String retour="";
//		Iterator<User> it = this.setUsers.iterator() ;
//		while(it.hasNext()){
//			retour+= it.next().toString() +"\n";
//		}
//		return retour;
	}
	public void valueChanged(ListSelectionEvent arg0) {
		System.out.println("to");
	}
}
