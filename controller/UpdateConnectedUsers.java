package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import model.ConnectedUsers;
import model.User;
import user.MessageUser.typeConnect;


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
	public void updateUser(User user){
		if(user.getEtat()==typeConnect.CONNECTED){
			users.getListUsers().removeElement(user); // Pour la mise à jour de la date d'actualisation
			users.getListUsers().addElement(user);
		}
		else {
			users.getListUsers().removeElement(user);
		}
//		if (users.getListUsers().)
//		users.getSetUsers().remove(user);
//		if (user.getEtat()==typeConnect.CONNECTED){ // si c'est un message de deconnexion 
//			users.getSetUsers().add(user);
//		}
	}
	
	/**
	 * Check if the users has left the multicast group.
	 */
	public void detectDeconnection(){
	Date now = new Date();
	ArrayList<User> usersToRemove = new ArrayList<User>();
	if (!this.users.getListUsers().isEmpty()) {  // Si la liste d'users n'est pas vide
		for (int i = 0; i < this.users.getListUsers().getSize() ; i++) {
			User user = this.users.getListUsers().elementAt(i);
			if (now.getTime() - user.getMiseAjour().getTime() > this.timerLost) {
				System.out.println("User déconnecté: "+ user.toString());
				usersToRemove.add(user);
			}
		}
		// usersToRemove.forEach(item->this.setUsers.remove(item));
		for(User user : usersToRemove){
			this.users.getListUsers().removeElement(user);
		}
	}
}
//	public void detectDeconnection(){
//		Date now = new Date();
//		ArrayList<User> usersToRemove = new ArrayList<User>();
//		if (!this.users.getSetUsers().isEmpty()) {  // Si le set d'users n'est pas vide
//			Iterator<User> it = this.users.getSetUsers().iterator() ;
//			while(it.hasNext()){
//				User user = it.next();
//				//System.out.println(" difference  : " +  (now.getTime() - user.getMiseAjour().getTime() ) );
//				if( (now.getTime() - user.getMiseAjour().getTime()) > this.timerLost){
//					System.out.println("User déconnecté: "+ user.toString());
//					usersToRemove.add(user);
//				}
//			}
//			// usersToRemove.forEach(item->this.setUsers.remove(item));
//			for(User user : usersToRemove){
//				this.users.getSetUsers().remove(user);
//			}
//		}
//	}
	
	
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
