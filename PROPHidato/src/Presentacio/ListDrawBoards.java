package Presentacio;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;

public class ListDrawBoards extends JFrame {

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
					ListDrawBoards frame = new ListDrawBoards();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ListDrawBoards() {
		
	}

	/**
	 * Create the frame.
	 */
	public ListDrawBoards(DrawView dv) throws NoSuchFileException{
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
		
		DefaultListModel<String> modelHidato = new DefaultListModel<>();  
		DefaultListModel<String> modelBoard = new DefaultListModel<>();  
		String[] hidatoList = ControlPresentacio.getInstance().listHidatos();
		String[] boardList = ControlPresentacio.getInstance().listBoards();
		for ( int i = 0; i < hidatoList.length; i++ ){
			modelHidato.addElement(hidatoList[i]);
		}
		for ( int i = 0; i < boardList.length; i++ ){
			modelBoard.addElement(boardList[i]);
		}
		
		JTextPane txtpnSelectOneHidato = new JTextPane();
		txtpnSelectOneHidato.setEditable(false);
		txtpnSelectOneHidato.setBackground(SystemColor.menu);
		txtpnSelectOneHidato.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnSelectOneHidato.setText("Select one hidato or board");
		

		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		
		
		JList<String> boardTab = new JList<>(modelBoard);
		tabbedPane.addTab("Boards", null, boardTab, null);
		boardTab.setFont(new Font("Tahoma", Font.PLAIN, 24));
		boardTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		boardTab.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JList<String> hidatoTab = new JList<>(modelHidato);
		tabbedPane.addTab("Hidatos", null, hidatoTab, null);
		hidatoTab.setFont(new Font("Tahoma", Font.PLAIN, 24));
		hidatoTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hidatoTab.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		contentPane.setLayout(gl_contentPane);
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedBoard = boardTab.getSelectedValue();
				String selectedHidato =  hidatoTab.getSelectedValue();
				System.out.println("Name: " + selectedHidato);
				String[] params = new String[] {};
				String[][] matriu = new String[][] {};
				ArrayList<String> par = new ArrayList<>();
				ArrayList<ArrayList<String>> mat = new ArrayList<ArrayList<String>>();
				try {
					if(tabbedPane.getSelectedIndex() == 0) ControlPresentacio.getInstance().loadBoard(selectedBoard, par, mat); 
					else ControlPresentacio.getInstance().loadHidato(selectedHidato, par, mat);
					params = par.toArray(new String[0]);
					String[][] m = new String[mat.size()][mat.get(0).size()];
					for(int i = 0; i< mat.size(); i++)
						m[i] = mat.get(i).toArray(new String[0]);
					matriu = m;
					dv.setBoard(matriu);
					dv.setParams(params);
					setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				DrawView dview= new DrawView(params, matriu);
				dview.getFrame().setVisible(true);
				dv.getFrame().setVisible(false);
				dv.getFrame().dispose();
			}
		});
		loadButton.setFont(new Font("Tahoma", Font.PLAIN, 26));

		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(txtpnSelectOneHidato, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE)
							.addComponent(loadButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnSelectOneHidato, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(loadButton, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE))
		);
	}
}
