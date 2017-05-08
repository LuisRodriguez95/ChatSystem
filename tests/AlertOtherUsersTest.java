package tests;

import static org.junit.Assert.*;

import java.net.InetAddress;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import communication.User;
import communication.User.typeConnect;

import tcp.AlertOthersUsers;
import tcp.CheckConnectedUsers;

public class AlertOtherUsersTest {

	protected AlertOthersUsers alert;
	protected CheckConnectedUsers check;
	InetAddress ip;
	
	@Before
	public void setUp() throws Exception {
		ip = InetAddress.getByName("228.5.6.7");
		User localUser = new User("Joris", ip, 3322, typeConnect.CONNECTED);
		alert = new AlertOthersUsers(ip, 2233, localUser );
		check = new CheckConnectedUsers(ip, 2233, localUser);

		//check.startChecker();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSendSingleUser() {
		Object obj = null;
		final User monuser= new User("Michel", ip, 8000, typeConnect.CONNECTED);
		Thread envoi = new Thread(new Runnable() {
			public void run() {
				alert.send(monuser);
				
			}
		});
		

		envoi.start();
		obj = check.recv();
		System.out.println("samere");
	
		envoi.interrupt();
		assertEquals(monuser, (User)obj);
		
//		for (int i = 0; i < 100; i++) {
//			User monuser= new User("Michel", addr, 8000+i, typeConnect.CONNECTED);
//			alert.send(monuser);
//		}
//		envoi.start();
//		Object obj = check.recv();
	}
	
	@Test
	public void testSendMultipleUsers() {
		Object obj = null;
		final User monuser= new User("Michel", ip, 8000, typeConnect.CONNECTED);
		Thread envoi = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					User monuser= new User("Michel", ip, 8000+i, typeConnect.CONNECTED);
					alert.send(monuser);
				}
			}
		});
		

		envoi.start();
		
		obj = check.recv();
	
		envoi.interrupt();
		assertEquals(monuser, (User)obj);
		

	}

}