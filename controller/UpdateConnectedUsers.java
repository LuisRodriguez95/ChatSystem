package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;

import communication.User;
import communication.User.typeConnect;
import interfaces.UserUpdater;
import model.ConnectedUsers;


/**
 * This class update the model ( ConnectedUsers ).  <br>
 * This class start a thread to update the ConnectedUsers list in case of deconnection.
 * @author chivunito
 *
 */
public class UpdateConnectedUsers implements Runnable, UserUpdater {

	private final ConnectedUsers users ;

	private HashMap<User, Date> usersdate = new HashMap<User, Date>(); 
	
	private long timerLost=4000;

	public UpdateConnectedUsers(ConnectedUsers users){  
		this.users=users;
	}

	public void startDetection() {
		new Thread(this).start();
	}

	public void updateUser(User user){
		if(user.getEtat()==typeConnect.CONNECTED){
			if (users.containsUser(user)) { // le user est déjà connu, 
				this.usersdate.put(user, new Date());
			}
			else {
				this.users.getListUsers().addElement(user);
				this.usersdate.put(user, new Date());
			}
		}
		else {
			users.getListUsers().removeElement(user);
			this.usersdate.remove(user);
		}
	}

	/**
	 * Check if the users has left the multicast group.
	 */

	public void detectDeconnection(){
		Date now = new Date();
		ArrayList<User> usersToRemove = new ArrayList<User>();
		if (!this.usersdate.isEmpty()) {  // Si la liste d'users n'est pas vide
			for(Map.Entry<User, Date> entry : this.usersdate.entrySet()){
				if (now.getTime() - entry.getValue().getTime() > this.timerLost) {  // rajouter la condition
					System.out.println("User déconnecté: "+ entry.getKey());
					usersToRemove.add(entry.getKey());
				}
			}
			for(User user : usersToRemove){
				this.users.getListUsers().removeElement(user);
				this.usersdate.remove(user);
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

	//	public static void main(String[] args) {
	//		InetAddress ip = null;
	//		try {
	//			ip=InetAddress.getLocalHost();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		User user1= new User("Denis", ip, 8000, typeConnect.CONNECTED);
	//		UpdateConnectedUsers.getInstance().updateUser(user1);
	//		while(true){
	//			System.out.println(ConnectedUsers.getInstance().toString());
	//			try {
	//				Thread.sleep(1000);
	//			} catch (InterruptedException e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
	//		}
	//	}

}
