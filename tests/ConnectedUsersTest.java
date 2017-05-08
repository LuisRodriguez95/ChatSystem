package tests;

import static org.junit.Assert.assertEquals;

import java.net.InetAddress;
import java.net.UnknownHostException;

import model.ConnectedUsers;

import org.junit.Test;

import communication.User;
import communication.User.typeConnect;

public class ConnectedUsersTest {


	@Test
 	public void testContainsKnownUser() {
 		ConnectedUsers con = new ConnectedUsers();
		
		User us1= null;
		try {
			us1 = new User("lachaume", InetAddress.getLocalHost(), 1324, typeConnect.CONNECTED);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		con.getListUsers().addElement(us1);
		assertEquals(con.containsUser(us1), true);
	}
	
	@Test
	public void testContainsUknonwUser() {
		ConnectedUsers con = new ConnectedUsers();
		InetAddress addLoc= null;
		try {
			addLoc = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		User us1 = new User("lachaume", addLoc, 1224, typeConnect.CONNECTED);
		User us2 = new User("thibaut", addLoc, 1225, typeConnect.CONNECTED);
		
		con.getListUsers().addElement(us1);
		assertEquals(con.containsUser(us2),false);
	}

}