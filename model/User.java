package model;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.Date;

import user.MessageUser.typeConnect;
public class User implements Serializable{

	private String pseudo; 
	private final InetAddress ip;
	private final int port; 
//	private final int portFile;
	private typeConnect etat;
	private Date miseAjour;
	private String statut;
	
	public User(String pseudo, InetAddress ip, int port, typeConnect etat) {
		this.pseudo = pseudo;
		this.ip = ip;
		this.port = port;
		this.etat = etat;
		this.miseAjour = new Date();
	//this.portFile = portFile;
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

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		System.out.println("Changement de pseudo de : "+ this.pseudo +" : " + this.statut + " > " + statut);
		this.statut = statut;getClass();
	}
	
}
