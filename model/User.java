package model;

import java.net.InetAddress;
import java.util.Date;
import user.MessageUser.typeConnect;
public class User{

	private String pseudo; 
	private InetAddress ip;
	private int port; 
	private typeConnect etat;
	private Date miseAjour;
	private String statut;
	
	public User(String pseudo, InetAddress ip, int port, typeConnect etat) {
		this.pseudo = pseudo;
		this.ip = ip;
		this.port = port;
		this.etat = etat;
		this.miseAjour = new Date();
	}
	
	public String toString() {
		return "User [pseudo=" + pseudo + ", ip=" + ip + ", port=" + port
				+ ", etat=" + etat + ", miseAjour=" + miseAjour.toString()  + "]";
	}


	public boolean equals(Object user){
		return this.getPseudo().contentEquals(((User)user).getPseudo());
	}
	
	 public int hashCode() {
		 return this.pseudo.hashCode();
	   } 
	 	
	public String getPseudo() {
		return pseudo;
	}
	
	public InetAddress getIp() {
		return ip;
	}


	public int getPort() {
		return port;
	}

	public typeConnect getEtat() {
		return etat;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public Date getMiseAjour() {
		return miseAjour;
	}
	
	public void setMiseAjour() {
		Date date = new Date();
		this.miseAjour = date;
	}
	
}
