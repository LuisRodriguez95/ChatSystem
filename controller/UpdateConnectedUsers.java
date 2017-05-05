package controller;

import java.util.ArrayList;
import java.util.Date;

import model.ConnectedUsers;
import model.UserDate;

import communication.User;
import communication.User.typeConnect;


/**
 * This class update the model ( ConnectedUsers ).  <br>
 * This class start a thread to update the ConnectedUsers list in case of deconnection.
 * @author chivunito
 *
 */
public class UpdateConnectedUsers implements Runnable {

	/**
	 * The connected users representation
	 */
	private ConnectedUsers users = ConnectedUsers.getInstance();
	
	/**
     * Time to detect a connection lost with an user
     */
	private long timerLost=4000;
	
	private static UpdateConnectedUsers instance = new UpdateConnectedUsers();   
	public static UpdateConnectedUsers getInstance()
	{	return instance;
	}
	private UpdateConnectedUsers(){   //Private constructor to implement Singleton design pattern
		new Thread(this).start();
	}
	
	
	/**
	 * update the user in the connected users Set : 
	 * <ul>
	 * <li> if it's a connected users, update its date of connection
	 * <li> otherwise, remove the users from the connected users set
	 * <ul>
	 * @param user
	 */
	public void updateUser(UserDate user){
		if(user.getUser().getEtat()==typeConnect.CONNECTED){
			users.getListUsers().addElement(user);
		}
		else {
			users.getListUsers().removeElement(user);
		}
	}
	
	public void updateDate(UserDate user){
		user.setDate(new Date());
	}
	
	/**
	 * Check if the users has left the multicast group.
	 */
	public void detectDeconnection(){
	Date now = new Date();
	ArrayList<UserDate> usersToRemove = new ArrayList<UserDate>();
	if (!this.users.getListUsers().isEmpty()) {  // Si la liste d'users n'est pas vide
		for (int i = 0; i < this.users.getListUsers().getSize() ; i++) {
			UserDate user = this.users.getListUsers().elementAt(i);
			if (now.getTime() - user.getDate().getTime() > this.timerLost) {
				System.out.println("User déconnecté: "+ user.toString());
				usersToRemove.add(user);
			}
		}
		// usersToRemove.forEach(item->this.setUsers.remove(item));
		for(UserDate user : usersToRemove){
			this.users.getListUsers().removeElement(user);
		}
	}
}	
	
	public void run() {
		while(true){
			try {
				Thread.sleep(this.timerLost);
				detectDeconnection();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
