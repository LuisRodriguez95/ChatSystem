package tcp;

import interfaces.MessageChannel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * TCPserver est le serveur d'écoute pour les messages envoyés par les autres utilisateurs
 * @author chivunito
 */
public class TCPServer implements Runnable {
	private MessageChannel listener;
	private ServerSocket welcomeSocket; 
	private final int port;
	
	public TCPServer(int port){
		this.port=port;
		try {
			this.welcomeSocket = new ServerSocket(this.port);
		} catch (IOException e) {
			System.out.println("Erreur à l'ouverture du Socket Serveur\n");
			e.printStackTrace();
		}
	}
	
	public void setListener(MessageChannel listener){
		this.listener=listener;
	}

	public void run() {
		while(true){
			try {
				Socket socket = this.welcomeSocket.accept();
				CanalReception canal = new CanalReception(socket);
				canal.setListener(this.listener);
				canal.readMessage();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}

}
