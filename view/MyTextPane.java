package view;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

public class MyTextPane implements Runnable {

    private JFrame frm;
    private JScrollPane jsp;
    private JTextPane jta;
    private StringBuilder buildSomething = new StringBuilder();
    private StringBuilder buildSomething1 = new StringBuilder();
    final String myBirthday = "Birthday";

    public MyTextPane() {
        for (int i = 0; i < 10; i++) {
            buildSomething.append("<h1 style=\"border-style:solid\">" + myBirthday + "</span>");
            buildSomething1.append("<html><b style=\"color:pink\">" + myBirthday + "</span><html>");
        }
        jta = new JTextPane();
        jta.setContentType("text/html");
        jta.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jta.setText(myBirthday);
        jsp = new JScrollPane(jta);
        jsp.setPreferredSize(new Dimension(250, 450));
        frm = new JFrame("awesome");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(new BorderLayout());
        frm.add(jsp, BorderLayout.CENTER);
        frm.setLocation(100, 100);
        frm.pack();
        frm.setVisible(true);
        new Thread(this).start();
    }

    public void run() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                jta.setText(buildSomething.toString());
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MyTextPane fs = new MyTextPane();
            }
        });
    }
}