package model;

import java.io.Serializable;

public class Message implements Serializable {
	private final String data;
	private final User expediteur;
	
	public Message(User expediteur, String data){
		this.data=data;
		this.expediteur=expediteur;
	}
	
	public String toString(){
		return this.expediteur.getPseudo() + " : " + this.data;
	}

	public String getData() {
		return data;
	}
	
	public User getExpediteur() {
		return expediteur;
	}
}
