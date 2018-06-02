package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import Persistencia.ControlPersistencia;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListHidatos extends JFrame {

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
		txtpnSelectOneHidato.setEditable(false);
		txtpnSelectOneHidato.setBackground(SystemColor.menu);
		txtpnSelectOneHidato.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnSelectOneHidato.setText("Select Hidato");
		
		JButton playButton = new JButton("Play");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = list.getSelectedValue();
				String[] params = new String[] {};
				String[][] matriu = new String[][] {};
				ControlPresentacio.getInstance().loadHidato(selected, params, matriu);
				GameView gv = new GameView();
				gv.setBoard(matriu);
				gv.setParams(params);
				gv.getFrame().setVisible(true);
			}
		});
		playButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
						.addComponent(txtpnSelectOneHidato, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
						.addComponent(playButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnSelectOneHidato, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(playButton, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
