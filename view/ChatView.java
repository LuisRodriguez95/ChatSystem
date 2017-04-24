package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import model.ConnectedUsers;
import model.Conversation;
import model.User;
import model.UserMessage;
import view.ConnectedUsersWindow.MyListDataListener;

public class ChatView implements ListSelectionListener{
	static DefaultListModel<UserMessage> messages;
	public static void createFrame(final String user)
    {
        EventQueue.invokeLater(new Runnable()
        {
            
            public void run()
            {
                JFrame frame = new JFrame(user);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                try 
                {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                   e.printStackTrace();
                }
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                final JTextPane textArea = new JTextPane();
                //textArea.setWrapStyleWord(true);
                textArea.setPreferredSize(new Dimension(400, 400));
                
                final SimpleAttributeSet local = new SimpleAttributeSet();
                StyleConstants.setAlignment(local, StyleConstants.ALIGN_LEFT);
                StyleConstants.setForeground(local, Color.black);

                final SimpleAttributeSet remote = new SimpleAttributeSet();
                StyleConstants.setAlignment(remote, StyleConstants.ALIGN_RIGHT);
                StyleConstants.setForeground(remote, Color.blue);
                StyleConstants.setLeftIndent(remote, 15);
                StyleConstants.setBackground(remote, Color.white);

                final StyledDocument textA = textArea.getStyledDocument();
                
                textArea.setEditable(false);
                textArea.setFont(Font.getFont(Font.SANS_SERIF));
                JScrollPane scroller = new JScrollPane(textArea);
                scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                JPanel inputpanel = new JPanel();
                inputpanel.setLayout(new FlowLayout());
                final JTextField input = new JTextField(20);
                
                messages = new DefaultListModel<UserMessage>();
                messages = Conversation.getInstance().getListUserMessage();

                
                messages.addListDataListener(new ListDataListener() {
					
					@Override
					public void intervalRemoved(ListDataEvent e) {
						System.out.println("changement2");
						
					}
					
					@Override
					public void intervalAdded(ListDataEvent e) {

		                textArea.setText("");
						System.out.println("changement3");
						for (int i = 0;i<messages.getSize();i++){
							if (messages.get(i).getUser().getPseudo()=="Luis"){
								try {
					                textArea.setParagraphAttributes(remote, true);
									textA.insertString(textA.getLength(), messages.get(i).getMessage() + "\n", remote);
								} catch (BadLocationException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							else{
								try {
					                textArea.setParagraphAttributes(local, true);
									textA.insertString(textA.getLength(), messages.get(i).getMessage() + "\n", local);
								} catch (BadLocationException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						
						System.out.println("changement3");
						
					}
					
					@Override
					public void contentsChanged(ListDataEvent e) {
						System.out.println("changement");
						
					}
				});
   
                JButton button = new JButton("Enter");
                button.addActionListener(new ActionListener() {
					
					
					public void actionPerformed(ActionEvent e) {
						String text = input.getText();
						if (text.equals("1")){
							try {
				                textArea.setParagraphAttributes(remote, true);
								textA.insertString(textA.getLength(), text+ "\n", remote);
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else{
							try {
				                textArea.setParagraphAttributes(local, true);
								textA.insertString(textA.getLength(), text+ "\n", local);
							} catch (BadLocationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}
				});
                button.setDefaultCapable(true);
                DefaultCaret caret = (DefaultCaret) textArea.getCaret();
                caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
                panel.add(scroller);
                inputpanel.add(input);
                inputpanel.add(button);
                panel.add(inputpanel);
                frame.getContentPane().add(BorderLayout.CENTER, panel);
                frame.pack();
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
                frame.setResizable(false);
                input.requestFocus();
            }
        });
    }
	
	
	
//	public static void main(String[] args) {
//		createFrame("Luis");
//	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
