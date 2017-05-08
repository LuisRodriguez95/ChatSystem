package view;

import interfaces.CanalVuesController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ConnectWindow extends Observable {

	private final JFrame frame;
	/** a label for the name */
	private final JLabel labelUser, labelStatut;
	/** a textfield for the name */
	private final JTextField textUser, textStatut;
	/** a button to perform an action: e.g. say hello (TBD) */
	private final JButton button;

	private CanalVuesController listener;

	public ConnectWindow() {
		frame = new JFrame();
		labelUser = new JLabel("User");
		labelStatut = new JLabel("Statut");

		textStatut = new JTextField("");
		textUser = new JTextField("");
		button = new JButton("Connect");
		initComponents();
	}

	private void initComponents() {

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String user = textUser.getText();
				String statut = textStatut.getText();
				frame.dispose();
				listener.setLocalUser(user);
				listener.setLocalStatut(statut);
				listener.startChat();
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
								.addComponent(labelStatut).addComponent(button))
				.addComponent(textStatut));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(labelUser).addComponent(textUser)
								.addComponent(labelStatut).addComponent(textStatut))
				.addComponent(button));


		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
	public void setConnectListener(CanalVuesController ct) {
		this.listener = ct;
	}

}
