package Presentacio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;

public class RankingView extends JFrame {

	private JPanel contentPane;
	private JTable easyRanking;

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
	 */
	public RankingView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JTextPane txtpnRanking = new JTextPane();
		txtpnRanking.setBackground(SystemColor.menu);
		txtpnRanking.setFont(new Font("Tahoma", Font.PLAIN, 28));
		txtpnRanking.setEditable(false);
		txtpnRanking.setText("Ranking");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1884, Short.MAX_VALUE)
						.addComponent(txtpnRanking, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(txtpnRanking, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 905, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(76, Short.MAX_VALUE))
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
		ArrayList<String> usersEasy = new ArrayList<String>();
		ArrayList<String> scoresEasy = new ArrayList<String>();
		ControlPresentacio.getInstance().getEasyRanking(usersEasy, scoresEasy);
		for(int i = 0; i < usersEasy.size(); i++) {
			modelEasy.addRow(new Object[]{usersEasy.get(i), scoresEasy.get(i)});
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
		ArrayList<String> usersMedium = new ArrayList<String>();
		ArrayList<String> scoresMedium = new ArrayList<String>();
		ControlPresentacio.getInstance().getMediumRanking(usersMedium, scoresMedium);
		for(int i = 0; i < usersMedium.size(); i++) {
			modelMedium.addRow(new Object[]{usersMedium.get(i), scoresMedium.get(i)});
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
		ArrayList<String> usersHard = new ArrayList<String>();
		ArrayList<String> scoreshard = new ArrayList<String>();
		ControlPresentacio.getInstance().getHardRanking(usersHard, scoreshard);
		for(int i = 0; i < usersHard.size(); i++) {
			modelHard.addRow(new Object[]{usersHard.get(i), scoreshard.get(i)});
		}
		hardRanking.setRowHeight(30);
		TableColumnModel columnModelHard = hardRanking.getColumnModel();
		columnModelHard.getColumn(0).setPreferredWidth(1700);
		columnModelHard.getColumn(1).setPreferredWidth(100);
		
		hardPanel.add(hardRanking);
		contentPane.setLayout(gl_contentPane);
	}
}
