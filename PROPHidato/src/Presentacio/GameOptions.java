package Presentacio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOptions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOptions frame = new GameOptions();
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
	public GameOptions() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth()/2,(int)screenSize.getHeight()/2);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JInternalFrame internalFrame = new JInternalFrame("Game Options");
		internalFrame.getContentPane().setBackground(Color.WHITE);
		internalFrame.setLocation(100, 30);
		internalFrame.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		internalFrame.setSize(100+(int)screenSize.getWidth()/3,(int)screenSize.getHeight()/3);
		contentPane.add(internalFrame);
		
		JComboBox difficulyBox = new JComboBox();
		difficulyBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		difficulyBox.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Medium", "Hard"}));
		
		JTextPane txtpnSelectDifficulty = new JTextPane();
		txtpnSelectDifficulty.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnSelectDifficulty.setEditable(false);
		txtpnSelectDifficulty.setText("Select difficulty");
		
		JTextPane txtpnSelectCellType = new JTextPane();
		txtpnSelectCellType.setText("Select cell type");
		txtpnSelectCellType.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnSelectCellType.setEditable(false);
		
		JComboBox cellTypeBox = new JComboBox();
		cellTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Triangle", "Square", "Hexagon"}));
		cellTypeBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JTextPane txtpnSelectCellAdjacency = new JTextPane();
		txtpnSelectCellAdjacency.setText("Select cell adjacency");
		txtpnSelectCellAdjacency.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnSelectCellAdjacency.setEditable(false);
		
		JComboBox adjacencyBox = new JComboBox();
		adjacencyBox.setModel(new DefaultComboBoxModel(new String[] {"Borders", "Borders and angles"}));
		adjacencyBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton generateButton = new JButton("Generate");
		generateButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlPresentacio cp = ControlPresentacio.getInstance();
				String diff = difficulyBox.getSelectedItem().toString();
				String adj = adjacencyBox.getSelectedItem().toString();
				String cellType = cellTypeBox.getSelectedItem().toString();
				cp.generateGame(diff, cellType, adj);
			}
		});
		GroupLayout groupLayout = new GroupLayout(internalFrame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(87)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnSelectDifficulty, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnSelectCellType, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnSelectCellAdjacency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(adjacencyBox, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(cellTypeBox, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
						.addComponent(difficulyBox, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
					.addGap(152))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(503, Short.MAX_VALUE)
					.addComponent(generateButton, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addGap(52))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnSelectDifficulty, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(difficulyBox, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnSelectCellType, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(cellTypeBox, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnSelectCellAdjacency, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(adjacencyBox, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
					.addComponent(generateButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		internalFrame.getContentPane().setLayout(groupLayout);
		
		JButton loadHidatoButton = new JButton("Load Hidato");
		loadHidatoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Mostrar una llista dels hidatos

			}
		});
		loadHidatoButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		loadHidatoButton.setBounds(67, 434, 215, 66);
		contentPane.add(loadHidatoButton);
		
		JButton loadGameButton = new JButton("Load Game");
		loadGameButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
		loadGameButton.setBounds(653, 434, 215, 66);
		contentPane.add(loadGameButton);
		internalFrame.setVisible(true);

	}
}
