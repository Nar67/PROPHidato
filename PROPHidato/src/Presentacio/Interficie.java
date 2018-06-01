package Presentacio;

import javax.swing.JApplet;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class Interficie extends JApplet {

	/**
	 * Create the applet.
	 */
	public Interficie() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("Jugar");
		panel.add(btnNewButton);

	}

}
