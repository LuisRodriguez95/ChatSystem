package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import model.PasswordDTB;

public class ConnectWindow extends Observable {

	private final JFrame frame;
	/** a label for the name */
	private final JLabel labelUser, labelPwd;
	/** a textfield for the name */
	private final JTextField textUser, textPwd;
	/** a button to perform an action: e.g. say hello (TBD) */
	private final JButton button;

	private ConnectListener connectListener;

	public ConnectWindow() {
		frame = new JFrame();
		labelUser = new JLabel("User");
		labelPwd = new JLabel("Password");

		textPwd = new JTextField("");
		textUser = new JTextField("");
		button = new JButton("Connect");
		initComponents();
	}

	private void initComponents() {

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String user = textUser.getText();
				String pass = textPwd.getText();
				if (PasswordDTB.getHmap().containsKey(user)){
					String pwd = PasswordDTB.getHmap().get(user);
					if (pwd.equals(textPwd.getText())) {
						new ConnectedUsersWindow();
						connectListener.startChatProcess(user);
						frame.dispose();
					} else {
						System.out.println("surprise motherfucker " + user
								+ " mauvais user " + " le bon pwd est : " + pwd);
					}
				}
				else{
					PasswordDTB.addUser(user, pass);
					new ConnectedUsersWindow();
					connectListener.startChatProcess(user);
					frame.dispose();
				}

			}
		});

		GroupLayout layout = new GroupLayout(frame.getContentPane());
		frame.setLayout(layout);
		frame.setSize(400, 100);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addComponent(labelUser)
				.addComponent(textUser)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addComponent(labelPwd).addComponent(button))
				.addComponent(textPwd));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(labelUser).addComponent(textUser)
								.addComponent(labelPwd).addComponent(textPwd))
				.addComponent(button));

		// regarding the added components

		// the JFrame is visible now
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
/*
	public static void main(String[] args) {
		new PasswordDTB();
		ConnectWindow f = new ConnectWindow();
		f.connectListener = new ConnectListener() {

			@Override
			public void startChatProcess(String pseudo) {
				System.out.println("start chat process with pseudo " + pseudo);
				
			}

			@Override
			public void sendMessage(String message) {
				System.out.println("sendMessage " + message);
			}
		};

	}
*/
	public void setConnectListener(ConnectListener ct) {
		this.connectListener = ct;

	}

}
