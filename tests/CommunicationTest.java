package tests;

import static org.junit.Assert.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

import communication.Message;
import communication.User;
import communication.User.typeConnect;
import controller.SenderMessage;
import interfaces.MessageChannel;
import tcp.ContactSocket;
import tcp.TCPServer;

/**
 * Test de verification pour l'envoi et reception de messages,  
 * @author ThibautSarion
 *
 */
public class CommunicationTest {

	/**
	 * Envoi de 200 messages à la suite
	 */
	@Test
	public void envoiMessages() {
		InetAddress ip=null;
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		TCPServer server = new TCPServer(9001);
		CompteurMessage counter = new CompteurMessage();
		server.setListener(counter);
		server.start();


		User expediteur = new User("Testing", ip, 6000, typeConnect.CONNECTED);
		int sendmessages=200;
		for (int i = 0; i < sendmessages; i++) {
			ContactSocket sender = new ContactSocket(ip, 9001);
			Message message= new Message("test" + i, expediteur);
			sender.sendMessage(message);			
		}

		/**
		 * Attend que le thread de réception ait reçu tous les messages
		 */
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertEquals("Nb messages reçus, differents du nb de messages envoyés",sendmessages,counter.receivedmessage);
	}

	/**
	 *Classe qui écoute les threads de reception pour compter le nombre de messages reçus
	 */
	public class CompteurMessage implements MessageChannel{
		public int receivedmessage = 0;
		synchronized public void addReceivedMessage(Message message) {
			receivedmessage ++;
			//			System.out.println("Message received : "+ receivedmessage +" " + message.getData());
		}

		public void sendMessage(User contact, String data) {
			// TODO Auto-generated method stub

		}

		public void sendFile(User contact) {
			// TODO Auto-generated method stub

		}

	}
}
