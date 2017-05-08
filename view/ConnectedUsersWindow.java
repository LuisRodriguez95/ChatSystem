package view;

import interfaces.AlerterNewMessage;
import interfaces.CanalVuesController;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ConnectedUsers;

import communication.User;


public class ConnectedUsersWindow implements ListSelectionListener , AlerterNewMessage {
	/** a label for the name */
	JFrame frame = new JFrame();
	private JList<User> connectedUsersList;
	/** a button to perform an action: e.g. say hello (TBD) */
	public JButton buttonChat,buttonStatus,buttonDisconnect,buttonVide;
	private final DefaultListModel<User> listModel ;
	private CanalVuesController listener;
	private User user;
	private HashMap<User, Integer> renderer = new HashMap<User, Integer>();
	public void setListener(CanalVuesController listener) {
		this.listener = listener;
	}

	public ConnectedUsersWindow(User user, ConnectedUsers users){
		this.user = user;
		this.listModel=users.getListUsers();
		initComponents();
	}

	private void initComponents(){

		Container pane = frame.getContentPane();
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		frame.setTitle(user.getPseudo()) ;


		buttonStatus = new JButton("Change status");

		buttonStatus.addActionListener(new ActionListener() {   //SET LE STATUT

			public void actionPerformed(ActionEvent e) {
				String status = JOptionPane.showInputDialog(buttonChat,"Enter new status", null);
				ConnectedUsersWindow.this.listener.setLocalStatut(status);
			}
		});

		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(buttonStatus, c);

		buttonDisconnect = new JButton("Disconnect");
		buttonDisconnect.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				listener.disconnect();
				frame.dispose();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(buttonDisconnect, c);

		buttonVide = new JButton("VIde");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(buttonVide, c);

		listModel.addListDataListener(new MyListDataListener());


		connectedUsersList = new JList<User>(listModel);
	
		connectedUsersList.setCellRenderer(new DefaultListCellRenderer(){
			public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ) {
				User user = (User) value;
				this.setText(user.getPseudo() +" : "+ user.getStatut());
				if (renderer.containsKey(user)){
					if(renderer.get(user)>0){
						this.setBackground(new Color(215, 189, 226));
						this.setText(user.getPseudo() +" ["+renderer.get(user)+" message(s) to read] : "+ user.getStatut());
					}
				}
				else if(isSelected){
					this.setBackground(new Color(162, 217, 206));
				}
				else{
					this.setBackground(Color.white);
				}
	            return this;
	        }
		});
		connectedUsersList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		connectedUsersList.setSelectedIndex(0);
		connectedUsersList.addListSelectionListener(this);

		JScrollPane listScrollPane = new JScrollPane(connectedUsersList);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 150;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(listScrollPane, c);
		buttonChat = new JButton("Chat");
		buttonChat.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				User user = connectedUsersList.getSelectedValue();

//				System.out.println(user.getPseudo()+ " voila le user : "+ user.toString());
				readMessage(user);
				listener.openChatView(user); 
				
			}
		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;       //aligned with button 2
		c.gridwidth = 2;   //2 columns wide
		c.gridy = 2;       //third row
		pane.add(buttonChat, c);


		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	class MyListDataListener implements ListDataListener {
		public void contentsChanged(ListDataEvent e) {
		}
		public void intervalAdded(ListDataEvent e) {
		}
		public void intervalRemoved(ListDataEvent e) {
		}
	}



	public void valueChanged(ListSelectionEvent arg0) {

	}

	public void newMessage(User user) {
		if (this.renderer.get(user)!=null){
			this.renderer.put(user, this.renderer.get(user)+1);
		}
		else{
			this.renderer.put(user, 1);
		}
		this.frame.repaint();
	}
	
	public void readMessage(User user){
		this.renderer.remove(user);
		this.frame.repaint();
	}


}
