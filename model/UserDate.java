package model;

import java.util.Date;

import communication.User;

public class UserDate {

	@Override
	public String toString() {
		return "UserDate [user=" + user + ", date=" + date + "]";
	}

	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private Date date;
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserDate(User user, Date date) {
		super();
		this.user = user;
		this.date = date;
	}
	
}
