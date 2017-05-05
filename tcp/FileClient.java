package tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class FileClient {
	public final Socket socket ;
	private final InetAddress address;
	private final int contactPort;

	public FileClient(InetAddress ip, int contactPort) {
		Socket socketinter = null;
		this.contactPort= contactPort;
		this.address=ip;
		try {
			socketinter = new Socket(this.address, this.contactPort);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.socket=socketinter;
	}

	public void sendfile(String filename){
		File file = new File(filename);
		// Get the size of the file
		//long length = file.length();	
		System.out.println(file.length());
		byte[] bytes = new byte[16 * 1024];
		InputStream in= null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutputStream out=null;
		try {
			out = this.socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int count;
		try {
			while ((count = in.read(bytes)) > 0) {
				out.write(bytes, 0, count);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		FileClient fClient= null;
		try {
			fClient = new FileClient(InetAddress.getLocalHost(), 6800);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fClient.sendfile("citoyenne.pdf");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
