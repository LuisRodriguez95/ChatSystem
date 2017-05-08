package tcp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import communication.Message;


public class ContactSocket implements Runnable{
	private Socket senderSocket;
	private Message messageToSend;
	
	public ContactSocket(InetAddress address,int contactPort){
		try{
			this.senderSocket = new Socket(address, contactPort);
		}
		catch (IOException e){
			System.out.println("Erreur à la création du socket client \n");
			e.printStackTrace();
		}
	}
	
	public void sendMessage(Message message) {
		this.messageToSend=message;
		new Thread(this).start();
	}

	public void run() {
		ObjectOutputStream objectOutput ;
		try {
			objectOutput = new ObjectOutputStream(senderSocket.getOutputStream());
			objectOutput.writeObject(this.messageToSend);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try {
			this.senderSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
