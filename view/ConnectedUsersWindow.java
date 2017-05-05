package view;

import interfaces.CanalVuesController;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
import communication.User.typeConnect;


public class ConnectedUsersWindow implements ListSelectionListener {
	/** a label for the name */
	 JFrame frame = new JFrame();
	 private JList<User> connectedUsersList;
	 /** a button to perform an action: e.g. say hello (TBD) */
	 public JButton buttonChat,buttonStatus,buttonDisconnect,buttonVide;
	 private DefaultListModel<User> listModel ;
	 private CanalVuesController listener;
	 private User user;
	 public void setListener(CanalVuesController listener) {
		this.listener = listener;
	}

	public ConnectedUsersWindow(User user){
		 this.user = user;
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
				new ConnectWindow();
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

        listModel = new DefaultListModel<User>();
        listModel = ConnectedUsers.getInstance().getListUsers();
        listModel.addListDataListener(new MyListDataListener());
    
        
		connectedUsersList = new JList<User>(listModel);
		connectedUsersList.setCellRenderer(new DefaultListCellRenderer(){

			private static final long serialVersionUID = 1L;

			public Component getListCellRendererComponent(JList<?> list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
						User user = (User)value;
				
						String userString = user.getPseudo();
				
						return super.getListCellRendererComponent(list, userString, index, isSelected,
						cellHasFocus);
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
				
				System.out.println(user.getPseudo()+ " voila le user : "+ user.toString());
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
	    	System.out.println("jjj");
	    }
	    public void intervalAdded(ListDataEvent e) {
	    	//System.out.println("qsd");
	    }
	    public void intervalRemoved(ListDataEvent e) {
	    	//System.out.println("sdf");
	    }
	}
	 
	

	public void valueChanged(ListSelectionEvent arg0) {
		//System.out.println("osef");
		
	}
	
	 public static void main(String[] args) {
		 User user = null;
			try {
				user = new User("luis", InetAddress.getLocalHost(), 5200, typeConnect.CONNECTED);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		    new ConnectedUsersWindow(user);

		  }


}
