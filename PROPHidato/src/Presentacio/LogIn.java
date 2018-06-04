package Presentacio;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

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
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 487, 302);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextPane txtpnWelcome = new JTextPane();
		txtpnWelcome.setBackground(Color.DARK_GRAY);
		txtpnWelcome.setForeground(Color.WHITE);
		txtpnWelcome.setBounds(20, 0, 461, 97);
		txtpnWelcome.setEditable(false);
		txtpnWelcome.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 36));
		txtpnWelcome.setText("Welcome to the HidatoGame");
		
		JTextPane txtpnUsername = new JTextPane();
		txtpnUsername.setBackground(Color.DARK_GRAY);
		txtpnUsername.setForeground(Color.WHITE);
		txtpnUsername.setEditable(false);
		txtpnUsername.setBounds(48, 122, 73, 20);
		txtpnUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtpnUsername.setText("Username");
		
		JTextPane txtpnPassword = new JTextPane();
		txtpnPassword.setBackground(Color.DARK_GRAY);
		txtpnPassword.setForeground(Color.WHITE);
		txtpnPassword.setEditable(false);
		txtpnPassword.setBounds(48, 153, 73, 20);
		txtpnPassword.setText("Password");
		txtpnPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		usernameText = new JTextField();
		usernameText.setBounds(170, 122, 135, 20);
		usernameText.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(170, 153, 135, 20);
		passwordField.setToolTipText("password");
		
		JButton logInButton = new JButton("Log In");
		logInButton.setBounds(102, 201, 91, 23);

		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setBounds(214, 201, 91, 23);
		
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(txtpnWelcome);
		frame.getContentPane().add(txtpnUsername);
		frame.getContentPane().add(usernameText);
		frame.getContentPane().add(txtpnPassword);
		frame.getContentPane().add(passwordField);
		frame.getContentPane().add(logInButton);
		frame.getContentPane().add(signUpButton);
		
		JTextPane errorText = new JTextPane();
		errorText.setBackground(Color.DARK_GRAY);
		errorText.setForeground(Color.RED);
		errorText.setEditable(false);
		errorText.setBounds(40, 230, 285, 32);
		frame.getContentPane().add(errorText);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LogIn.class.getResource("/img/xxx.png")));
		lblNewLabel.setBounds(325, 69, 156, 193);
		frame.getContentPane().add(lblNewLabel);

		
		

		
		logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(usernameText.getText().equals("")) {
            		errorText.setText("Please write a valid username");
            	}
            	else {
            		ControlPresentacio cp = ControlPresentacio.getInstance();
            		String username = usernameText.getText();
            		char[] password = passwordField.getPassword(); 
            		try {
						if(!cp.logInUser(username, password)) {
							errorText.setText("Username or password may be incorrect, please try again");
						}
						else {
							MainMenu nm = new MainMenu();
							nm.getFrame().setVisible(true);
							frame.dispose();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            		
            }
        });
		
		signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(usernameText.getText().equals("")) {
            		errorText.setText("Please write a valid username");
            	}
            	else {
            		ControlPresentacio cp = ControlPresentacio.getInstance();
            		String username = usernameText.getText();
            		char[] password = passwordField.getPassword();
            		try {
						if(!cp.signUpUser(username, password)) {
							errorText.setText("User already exists, pleasy try again");
						}
						else {
							MainMenu nm = new MainMenu();
							nm.getFrame().setVisible(true);
							frame.dispose();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            		
            }
        });
	}
}