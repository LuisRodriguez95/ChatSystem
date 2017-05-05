package model;

import java.io.Serializable;

public class Message implements Serializable {
	private final String data;
	private final communication.User expediteur;
	
	public Message(communication.User localUser, String data){
		this.data=data;
		this.expediteur=localUser;
	}
	
	public String toString(){
		return this.expediteur.getPseudo() + " : " + this.data;
	}

	public String getData() {
		return data;
	}
	
	public communication.User getExpediteur() {
		return expediteur;
	}
}
