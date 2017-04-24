package controller;

import java.util.Observer;

import view.ConnectListener;
import view.ConnectWindow;

public class mainage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final ConnectWindow view = new ConnectWindow();
		final ConnectListener ct = new ChatProcess();
		view.setConnectListener(ct);
	}

}
