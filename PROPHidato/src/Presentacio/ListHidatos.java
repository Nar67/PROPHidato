package Presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ListHidatos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListHidatos frame = new ListHidatos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ListHidatos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    setVisible(false);
                    dispose();
                }
        });
		setBounds(100, 100, 956, 655);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		DefaultListModel<String> model = new DefaultListModel<>();  
		JList<String> list = new JList<>(model);
		list.setFont(new Font("Tahoma", Font.PLAIN, 24));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] hidatoList = ControlPresentacio.getInstance().listHidatos();
		for ( int i = 0; i < hidatoList.length; i++ ){
			model.addElement(hidatoList[i]);
		}
		list.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JTextPane txtpnSelectOneHidato = new JTextPane();
		txtpnSelectOneHidato.setForeground(Color.WHITE);
		txtpnSelectOneHidato.setEditable(false);
		txtpnSelectOneHidato.setBackground(Color.DARK_GRAY);
		txtpnSelectOneHidato.setFont(new Font("Verdana", Font.BOLD, 30));
		txtpnSelectOneHidato.setText("Select an Hidato");
		
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = list.getSelectedValue();
				String[] params = new String[] {};
				String[][] matriu = new String[][] {};
				try {
					ControlPresentacio.getInstance().loadHidato(selected, params, matriu);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				GameView gv = new GameView(params, matriu);
				gv.getFrame().setVisible(true);
			}
		});
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		
		BasicArrowButton basicArrowButton = new BasicArrowButton(7);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(list, GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
								.addComponent(playButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(basicArrowButton, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
							.addComponent(txtpnSelectOneHidato, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
							.addGap(298))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtpnSelectOneHidato, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(13))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(basicArrowButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(playButton, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
