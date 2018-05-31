package Presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.Box;

public class LogIn {

	private JFrame frame;
	private JTextField usernameText;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 487, 302);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextPane txtpnWelcome = new JTextPane();
		txtpnWelcome.setBounds(139, 10, 193, 51);
		txtpnWelcome.setEditable(false);
		txtpnWelcome.setFont(new Font("Tahoma", Font.PLAIN, 42));
		txtpnWelcome.setText("Welcome");
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setBounds(84, 99, 73, 20);
		txtpnUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnUsername.setText("Username");
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setBounds(84, 142, 73, 20);
		txtpnPassword.setText("Password");
		txtpnPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		usernameText = new JTextField();
		usernameText.setBounds(181, 99, 135, 20);
		usernameText.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(181, 142, 135, 20);
		passwordField.setToolTipText("password");
		
		JButton logInButton = new JButton("Log In");
		logInButton.setBounds(84, 230, 91, 23);

		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setBounds(275, 230, 91, 23);
		
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(txtpnWelcome);
		frame.getContentPane().add(txtpnUsername);
		frame.getContentPane().add(usernameText);
		frame.getContentPane().add(txtpnPassword);
		frame.getContentPane().add(passwordField);
		frame.getContentPane().add(logInButton);
		frame.getContentPane().add(signUpButton);
		
		JTextPane errorText = new JTextPane();
		errorText.setForeground(Color.RED);
		errorText.setEditable(false);
		errorText.setBounds(84, 167, 285, 32);
		frame.getContentPane().add(errorText);
		
		
		
		
		logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(usernameText.getText().equals("")) {
            		errorText.setText("Please write a valid username");
            	}
            	else {
            		ControlPresentacio cp = ControlPresentacio.getInstance();
            		String username = usernameText.getText();
            		String password = passwordField.getText(); //deprecated 
            		if(!cp.logInUser(username, password)) {
            			
            		}
            	}
            		
            }
        });
	}
}
