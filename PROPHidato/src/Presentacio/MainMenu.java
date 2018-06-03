package Presentacio;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.xml.ws.Dispatch;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

public class MainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton createButton = new JButton("CREATE");
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 43));
		
		JButton playButton = new JButton("PLAY");
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 43));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameOptions go = new GameOptions();
				go.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JButton rankingButton = new JButton("RANKING");
		rankingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RankingView rv = null;
				try {
					rv = new RankingView();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				rv.setVisible(true);
				
			}
		});
		rankingButton.setFont(new Font("Tahoma", Font.PLAIN, 43));
		
		JButton logOutButton = new JButton("Log Out");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn login = new LogIn();
				frame.setVisible(false);
				login.getFrame().setVisible(true);
			}
		});
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(797)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(playButton, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
								.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
								.addComponent(rankingButton, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)))
						.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(797, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(201)
					.addComponent(playButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(183)
					.addComponent(createButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
					.addComponent(rankingButton, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(125)
					.addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
