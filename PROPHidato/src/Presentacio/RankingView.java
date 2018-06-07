package Presentacio;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RankingView extends JFrame {

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
					RankingView frame = new RankingView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public RankingView() throws IOException {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    setVisible(false);
                    dispose();
                }
        });
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JTextPane txtpnRanking = new JTextPane();
		txtpnRanking.setForeground(Color.WHITE);
		txtpnRanking.setBackground(Color.DARK_GRAY);
		txtpnRanking.setFont(new Font("Verdana", Font.BOLD, 50));
		txtpnRanking.setEditable(false);
		txtpnRanking.setText("HidatoGame                 Ranking");
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RankingView.class.getResource("/img/1stplace.png")));
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(RankingView.class.getResource("/img/1stplace.png")));
		
		BasicArrowButton backButton = new BasicArrowButton(7);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mm = new MainMenu();
				mm.getFrame().setVisible(true);
				dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(54)
							.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(290)
							.addComponent(label)
							.addGap(100)
							.addComponent(txtpnRanking, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
							.addGap(96)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1880, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(65)
							.addComponent(txtpnRanking, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
								.addComponent(backButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))))
					.addGap(49)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 907, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JPanel easyPanel = new JPanel();
		easyPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Easy", null, easyPanel, null);
		
		DefaultTableModel modelEasy = new DefaultTableModel(); 
		JTable easyRanking = new JTable(modelEasy);
		easyRanking.setShowVerticalLines(false);
		easyRanking.setBackground(Color.WHITE);
		easyRanking.setBorder(new CompoundBorder());
		easyRanking.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		easyRanking.setFont(new Font("Tahoma", Font.PLAIN, 26));
		modelEasy.addColumn("Username");
		modelEasy.addColumn("Score");
		
		HashMap<String, Integer> rankEasy = ControlPresentacio.getInstance().getEasyRanking();
		
	    Iterator<Entry<String, Integer>> itE = rankEasy.entrySet().iterator();
	    while (itE.hasNext()) {
	        Map.Entry<String, Integer> pair = (Entry<String, Integer>)itE.next();
	        modelEasy.addRow(new Object[]{pair.getKey(), pair.getValue()});
	        itE.remove(); // avoids a ConcurrentModificationException
	    }


		easyRanking.setRowHeight(30);
		TableColumnModel columnModelEasy = easyRanking.getColumnModel();
		columnModelEasy.getColumn(0).setPreferredWidth(1700);
		columnModelEasy.getColumn(1).setPreferredWidth(100);
		
		easyPanel.add(easyRanking);
		JPanel mediumPanel = new JPanel();
		mediumPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Medium", null, mediumPanel, null);
		
		DefaultTableModel modelMedium = new DefaultTableModel(); 
		JTable mediumRanking = new JTable(modelMedium);
		mediumRanking.setShowVerticalLines(false);
		mediumRanking.setBackground(Color.WHITE);
		mediumRanking.setBorder(new CompoundBorder());
		mediumRanking.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mediumRanking.setFont(new Font("Tahoma", Font.PLAIN, 26));
		modelMedium.addColumn("Username");
		modelMedium.addColumn("Score");
		
		
		HashMap<String, Integer> rankMedium = ControlPresentacio.getInstance().getMediumRanking();
		
	    Iterator<Entry<String, Integer>> itM = rankMedium.entrySet().iterator();
	    while (itM.hasNext()) {
	        Map.Entry<String, Integer> pair = (Entry<String, Integer>)itM.next();
	        modelMedium.addRow(new Object[]{pair.getKey(), pair.getValue()});
	        itM.remove(); // avoids a ConcurrentModificationException
	    }
		mediumRanking.setRowHeight(30);
		TableColumnModel columnModelMedium = mediumRanking.getColumnModel();
		columnModelMedium.getColumn(0).setPreferredWidth(1700);
		columnModelMedium.getColumn(1).setPreferredWidth(100);
		
		mediumPanel.add(mediumRanking);
		
		JPanel hardPanel = new JPanel();
		hardPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Hard", null, hardPanel, null);
		
		DefaultTableModel modelHard = new DefaultTableModel(); 
		JTable hardRanking = new JTable(modelHard);
		hardRanking.setShowVerticalLines(false);
		hardRanking.setBackground(Color.WHITE);
		hardRanking.setBorder(new CompoundBorder());
		hardRanking.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hardRanking.setFont(new Font("Tahoma", Font.PLAIN, 26));
		modelHard.addColumn("Username");
		modelHard.addColumn("Score");
		
		HashMap<String, Integer> rankHard = ControlPresentacio.getInstance().getHardRanking();
		
	    Iterator<Entry<String, Integer>> itH = rankHard.entrySet().iterator();
	    while (itH.hasNext()) {
	        Map.Entry<String, Integer> pair = (Entry<String, Integer>)itH.next();
	        modelHard.addRow(new Object[]{pair.getKey(), pair.getValue()});
	        itH.remove(); // avoids a ConcurrentModificationException
	    }
		hardRanking.setRowHeight(30);
		TableColumnModel columnModelHard = hardRanking.getColumnModel();
		columnModelHard.getColumn(0).setPreferredWidth(1700);
		columnModelHard.getColumn(1).setPreferredWidth(100);
		
		hardPanel.add(hardRanking);
		contentPane.setLayout(gl_contentPane);
	    }
	}
