package controller;

import interfaces.ConnectListener;
import view.ConnectWindow;


public class mainage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final ConnectWindow view = new ConnectWindow();
		final ConnectListener ct = new UserListController();
		view.setConnectListener(ct);

	}

}
