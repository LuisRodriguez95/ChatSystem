package tcp;

import interfaces.MessageChannel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.Message;

public class CanalReception implements Runnable{
	private MessageChannel listener;
	
	private Socket inputPipe;
	
	public CanalReception(Socket socket){
		this.inputPipe=socket;
	}

	public void readMessage() {
		new Thread(this).start();
	}

	public void setListener(MessageChannel listener){
		this.listener=listener;
	}
	
	public void run() {
		try {
			ObjectInputStream serverInputStream = new  ObjectInputStream(inputPipe.getInputStream());
			try {
				Message message = (Message)serverInputStream.readObject();
				listener.addReceivedMessage(message);
				System.out.println(message.toString());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.inputPipe.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
