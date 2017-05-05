package tcp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import interfaces.MessageChannel;

public class FileServer implements Runnable {
	private final ServerSocket serverSocket; 
	private final int port;

	public FileServer(int port){
		this.port=port;
		ServerSocket serverInterm = null;
		try {
			serverInterm = new ServerSocket(this.port);
		} catch (IOException e) {
			System.out.println("Can't setup server on this port number. ");
			e.printStackTrace();
		}
		this.serverSocket = serverInterm;
	}

	public void run() {
		while(true) {
			InputStream in = null;
			OutputStream out = null;
			Socket socket=null;

			try {
				socket = serverSocket.accept();
			} catch (IOException ex) {
				System.out.println("Can't accept client connection. ");
			}

			try {
				in = socket.getInputStream();
			} catch (IOException ex) {
				System.out.println("Can't get socket input stream. ");
			}

			try {
				out = new FileOutputStream("fichierreçu");
			} catch (FileNotFoundException ex) {
				System.out.println("File not found. ");
			}

			byte[] bytes = new byte[16*1024];

			int count;
			try {
				while ((count = in.read(bytes)) > 0) {
					out.write(bytes, 0, count);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void startServer() {
		new Thread(this).start();
		
	}

	public static void main(String[] args) {
		FileServer fs =new FileServer(6800);
		new Thread(fs).start();
	}

}
