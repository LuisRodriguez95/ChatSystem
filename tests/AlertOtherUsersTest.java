package tests;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.util.ArrayList;

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
	boolean finished;
	int j;
	ArrayList<Object> sendObj,recvObj;
	Object obj = null;
	
	@Before
	public void setUp() throws Exception {
		ip = InetAddress.getByName("228.5.6.7");
		User localUser = new User("Joris", ip, 3322, typeConnect.CONNECTED);
		alert = new AlertOthersUsers(ip, 2233, localUser );
		check = new CheckConnectedUsers(ip, 2233, localUser);
		finished = false;
		sendObj = new ArrayList<Object>();
		recvObj = new ArrayList<Object>();
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
		User monuser= new User("Michel", ip, 8000, typeConnect.CONNECTED);
		
		Thread rcv = new Thread(new Runnable() {
			public void run() {
				while (true){
					obj = check.recv();
					j++;
					recvObj.add(obj);
				}
			}
		});

		rcv.start();
		
		for (int i = 0; i < 20; i++) {
			monuser= new User("Michel"+i, ip, 8000, typeConnect.CONNECTED);
			alert.send(monuser);
			
			sendObj.add(monuser);
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		rcv.interrupt();
		System.out.println(j);
		assertEquals(recvObj,sendObj);
		

	}

}