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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

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
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.DARK_GRAY);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton createButton = new JButton("CREATE");
		createButton.setBounds(513, 345, 320, 94);
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 43));
		
		JButton playButton = new JButton("PLAY");
		playButton.setBounds(513, 108, 320, 94);
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 43));
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GameOptions go = new GameOptions();
				go.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JButton rankingButton = new JButton("RANKING");
		rankingButton.setBounds(513, 494, 320, 94);
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
		logOutButton.setBounds(23, 687, 121, 41);
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn login = new LogIn();
				frame.setVisible(false);
				login.getFrame().setVisible(true);
			}
		});
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel label = new JLabel("");
		label.setBounds(384, 465, 92, 140);
		label.setIcon(new ImageIcon(MainMenu.class.getResource("/img/Transparent_Gold_Cup_Trophy_PNG_Clipart.png")));
		frame.getContentPane().setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(868, 465, 92, 140);
		label_1.setIcon(new ImageIcon(MainMenu.class.getResource("/img/Transparent_Gold_Cup_Trophy_PNG_Clipart.png")));
		frame.getContentPane().add(label_1);
		frame.getContentPane().add(label);
		frame.getContentPane().add(playButton);
		frame.getContentPane().add(createButton);
		frame.getContentPane().add(rankingButton);
		frame.getContentPane().add(logOutButton);
		
		JTextArea txtrDondeHayAmor = new JTextArea();
		txtrDondeHayAmor.setText("Donde hay amor hay vida - Gandhi");
		txtrDondeHayAmor.setBounds(166, 697, 278, 22);
		frame.getContentPane().add(txtrDondeHayAmor);
	}
}
