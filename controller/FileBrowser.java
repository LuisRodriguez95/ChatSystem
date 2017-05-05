package controller;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import tcp.FileClient;


public class FileBrowser{
private final JFileChooser fcChooser;
	public FileBrowser() {
		this.fcChooser = new JFileChooser();
	}
	
	public String getFile() throws FileNotFoundException{
		int coderetour=this.fcChooser.showOpenDialog(null);
		String retour="";
		if ( coderetour == JFileChooser.APPROVE_OPTION){
			File file = fcChooser.getSelectedFile();
			retour= file.getAbsolutePath();
		}
		else{
			throw new FileNotFoundException();
		}
		return retour;
	}
	
	public static void main(String[] args) {
		FileBrowser fBrowser =new FileBrowser();
		String file ="";
		try {
			file = fBrowser.getFile();
		} catch (FileNotFoundException e) {
			System.out.println("no file selected");
		}
		FileClient fClient= null;
		try {
			fClient = new FileClient(InetAddress.getLocalHost(), 6800);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		fClient.sendfile(file);
	}
}
