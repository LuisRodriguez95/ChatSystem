package model;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ListUI;

import communication.User;
import communication.User.typeConnect;
import controller.UpdateConnectedUsers;

/**
 *  This class allows a representation of all the connected users according to the MessagesUsers received from the CheckConnectedUsers socket <br>
 *  This class implements the Singleton design pattern, ( we just need one list of connected user for an application ) 
 * @author chivunito
 */
public class ConnectedUsers implements ListSelectionListener {  

	/**
     * Set of users ( the hashCode is on the User's pseudo )
     */
	
	private DefaultListModel<UserDate> listUsers = new DefaultListModel<UserDate>();
	
	private static ConnectedUsers instance = new ConnectedUsers();   
	public static ConnectedUsers getInstance()
	{	return instance;
	}
	private ConnectedUsers(){   //Private constructor to implement Singleton design pattern
	}
	
	public boolean contains(UserDate user){
		
		return this.listUsers.contains(user);
		
	}
	
	public boolean containsUser(User user){

		boolean contains= false;
		for (int i =0; i<this.listUsers.getSize();i++){
			if (this.listUsers.get(i).getUser().equals(user)){
				contains = true;
			}
		}
		return contains;
		
	}
	
	public void setDate(UserDate UD){
		UserDate us = this.getUser(UD);
		us.setDate(new Date());
	}
	
	public UserDate getUser(UserDate user){
		UserDate theUser = null;
		for (int i =0; i<this.listUsers.getSize();i++){
			if (this.listUsers.get(i).equals(user)){
				theUser = this.listUsers.get(i);
			}
		}
		return theUser;
		
	}
	
	public DefaultListModel<UserDate> getListUsers() {
		return listUsers;
	}
	
	public String toString(){
		return this.listUsers.toString();
		
	}
	
	public void valueChanged(ListSelectionEvent arg0) {
		System.out.println("to");
	}
	
	public static void main(String[] args) {
		ConnectedUsers s = new ConnectedUsers();
		User u1=null;
		User u2 =null;
		try {
			u1 = new User("h1", InetAddress.getLocalHost(), 4562, typeConnect.CONNECTED);
			u2 = new User("h2", InetAddress.getLocalHost(), 4542, typeConnect.CONNECTED);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserDate UD1 = new UserDate(u1, new Date());
		UserDate UD2 = new UserDate(u2, new Date());
	
		s.getListUsers().addElement(UD1);
		s.getListUsers().addElement(UD2);
		UserDate user = s.getUser(UD1);
		System.out.println("user : " + user.toString());
		System.out.println(s.toString());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.setDate(UD1);
		System.out.println("user : " + user.toString());
		System.out.println(s.toString());
		UpdateConnectedUsers.getInstance().updateDate(UD1);
	}
}
