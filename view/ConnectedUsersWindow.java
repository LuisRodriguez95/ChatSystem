package view;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.ChatProcess;

import model.ConnectedUsers;
import model.PasswordDTB;
import model.User;


public class ConnectedUsersWindow extends JFrame implements ListSelectionListener {
	/** a label for the name */
	//JFrame frame = new JFrame();
	 private JList<User> connectedUsersList;
	 /** a button to perform an action: e.g. say hello (TBD) */
	 public static JButton buttonChat,buttonStatus,buttonDisconnect,buttonVide;
	 private DefaultListModel<User> listModel ;
	 
	 
	 public ConnectedUsersWindow(){
		 
		 initComponents();
	 }
	 
	 private void initComponents(){
		 
		Container pane = this.getContentPane();
		pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		 
	    pane.setLayout(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
		    

		buttonStatus = new JButton("Change status");
		
		buttonStatus.addActionListener(new ActionListener() {   //SET LE STATUT
			
			public void actionPerformed(ActionEvent e) {
				String status = JOptionPane.showInputDialog(buttonChat,"Enter new status", null);
				ChatProcess.localUser.setPseudo(status);
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
				new ConnectWindow();
				ConnectedUsersWindow.this.dispatchEvent(new WindowEvent(ConnectedUsersWindow.this, WindowEvent.WINDOW_CLOSING));
				
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

//		DefaultListModel<Object> listModel = new DefaultListModel<Object>();
//		listModel = Params.getListModel();
//		listModel.addElement("samere");

        //listModel.addListDataListener(new MyListDataListener());

        listModel = new DefaultListModel<User>();
        listModel = ConnectedUsers.getInstance().getListUsers();
        listModel.addListDataListener(new MyListDataListener());
        System.out.println("sam"+listModel.toString());
        
		connectedUsersList = new JList<User>(listModel);
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
				String obj = connectedUsersList.getSelectedValue().toString();
				System.out.println(obj);
				ChatView.createFrame(obj);
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
	    
	    

		 //connectedUsersList.addListSelectionListener(this);
		 
		 // the JFrame is visible now
	    this.pack();
		this.setVisible(true);
		//this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	 }
	 
	class MyListDataListener implements ListDataListener {
	    public void contentsChanged(ListDataEvent e) {
	    	System.out.println("jjj");
	    }
	    public void intervalAdded(ListDataEvent e) {
	    	System.out.println("qsd");
	    }
	    public void intervalRemoved(ListDataEvent e) {
	    	System.out.println("sdf");
	    }
	}
	 
	 public static void main(String[] args) {
		    new PasswordDTB();
		    ConnectedUsersWindow f = new ConnectedUsersWindow();
		  }

	public void valueChanged(ListSelectionEvent arg0) {
		System.out.println("osef");
		
	}
}
