package tests;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import model.ConnectedUsers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import communication.User;
import communication.User.typeConnect;

public class ConnectedUsersTest {


	@Test
	public void testContainsUser() {
		ConnectedUsers con = new ConnectedUsers();
//		User us1= null;
//		try {
//			us1 = new User("lachaume", InetAddress.getLocalHost(), 1324, typeConnect.CONNECTED);
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		con.getListUsers().addElement(us1);
//		assertEquals(con.containsUser(us1), true);
	}

}
