package tcp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;


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
		}
		this.socket=socketinter;
	}

	public void sendfile(String filename){
		File file = new File(filename);
		byte[] bytes = new byte[16 * 1024];
		InputStream in= null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		OutputStream out=null;
		try {
			out = this.socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
