package tests;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import communication.Message;
import communication.User;
import communication.User.typeConnect;
import model.ListeConversations;

/**
 * Test de la liste des conversations, il s'agit d'une hashmap<User,Message> : User représente le contact, Message représente le message envoyé ou reçu.
 * @author chivunito
 *
 */
public class ListeConversationTest {
	final User localUser;
	final User remoteUser;
	final User remoteUser2;
	ListeConversations convos ;
	final Message sentmessage;
	final Message recvmessage;
	public ListeConversationTest(){
		User lUser=null;
		User rUser=null;
		User rUser2=null;
		try {
			lUser = new User("Thibaut",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
			rUser = new User("Luis",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
			rUser2 = new User("Fred",InetAddress.getLocalHost(),123456,typeConnect.CONNECTED);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("Bonjour");
		localUser=lUser;
		remoteUser=rUser;
		remoteUser2=rUser2;
		this.sentmessage = new Message("Message envoyé par le localUser", localUser);
		this.recvmessage = new Message("Message envoyé par le remoteUser", remoteUser);

	}

	@Before
	public void setUp(){
		convos = new ListeConversations();
	}

	@Test 
	public void testAjoutMessage() {
		convos.addReceivedMessage(recvmessage);
		assertEquals("un nouveau message", 1, convos.getConversation(remoteUser).size());
	}

	@Test 
	public void testGetconvo() {
		convos.addReceivedMessage(recvmessage);
		assertSame("un nouveau message", convos.getConversation(remoteUser).get(0),recvmessage);
	}

	@Test 
	public void testAddseveralsmessage() {
		convos.addReceivedMessage(recvmessage);
		convos.addSentMessageToConversation(remoteUser, sentmessage);
		assertEquals("Deux messages de la meme conversation",2, convos.getConversation(remoteUser).size());
	}

	@Test 
	public void addseveralConversations() {
		Message message2 = new Message("Message envoyé par le remoteUser2", remoteUser2);
		convos.addReceivedMessage(message2);
		convos.addReceivedMessage(recvmessage);
		assertEquals("Deux messages de conversations différentes",2, convos.size());
	}

}
