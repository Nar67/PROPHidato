package Presentacio;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;

import javafx.scene.layout.Border;

import javax.swing.SwingUtilities;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Checkbox;

public class DrawView {

	private JFrame frame;
	private String[][] board;
	private String[] params;
	private Vector<Vector<Polygon>> matrix;
	private Vector<Vector<Point>> centers;
	private String cellType;
	private String adjType;
	private int finalRows;
	private int finalCols;
	private boolean validated;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawView window = new DrawView();
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
	public DrawView() {
		setMatrix("T");
		board = new String[finalRows][finalCols];
		for(String[] b : board)
			Arrays.fill(b, "#");
		board[finalRows/2][finalCols/2] = "?";
		initialize();
	}
	
	public DrawView(String[] params, String[][] board) {
		this.params = params;
		this.board = board;
		initialize();
	}
	
	private boolean isNumeric(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) return false;
		}
		return true;
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void drawMatrix(Vector<Vector<Polygon>> matrix, Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
		g.setStroke(new BasicStroke(3));
		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        for(int i = 0; i < matrix.size(); i++) {
        	for(int j = 0; j < matrix.get(0).size(); j++) {
        		Polygon p = matrix.get(i).get(j);
        		if(board[i][j].equals("#")) {
        			g.setColor(Color.LIGHT_GRAY);
        			g.drawPolygon(p);
        			g.setColor(Color.BLACK);
        		}
        		else if(board[i][j].equals("*")) {
					g.setColor(Color.ORANGE);
					g.fillPolygon(p);
					g.setColor(Color.BLACK);
					g.drawPolygon(p);
        		}
        		if(isNumeric(board[i][j])) {
        			g.setColor(Color.BLACK);
        			int xOffset = centers.get(i).get(j).x - (4*board[i][j].length());
        			int yOffset = centers.get(i).get(j).y + (2*board[i][j].length());
        			g.drawString(board[i][j], xOffset, yOffset);
        			g.drawPolygon(p);
        		}
        	}
        }
        for(int i = 0; i < matrix.size(); i++) {
        	for(int j = 0; j < matrix.get(0).size(); j++) {
        		if(board[i][j].equals("?") || isNumeric(board[i][j])) {
        			Polygon p = matrix.get(i).get(j);
        			g.setColor(Color.BLACK);
        			g.drawPolygon(p);
        		}
        	}
		}
        g.setColor(Color.BLACK);
        g.drawPolygon(matrix.get(matrix.size()/2).get(matrix.get(0).size()/2) );
	}
	
	private void setCellType(String s) {
    	if(s.equals("Square")) cellType = "Q";
    	else if(s.equals("Triangle")) cellType = "T";
    	else if(s.equals("Hexagon")) cellType = "H";
	}
	
	private void setCellAdjacency(String s) {
		if(s.equals("Borders")) adjType = "C";
    	else if(s.equals("Borders & angles")) adjType = "CA";
	}
	
	private void setMatrix(String cellType) {
		int rows = (900/ControlPresentacio.getInstance().BORDER_SIZE)-1;
		int cols = (1600/ControlPresentacio.getInstance().BORDER_SIZE)-1;
		int h = (900/(int) (ControlPresentacio.getInstance().BORDER_SIZE * (Math.sqrt(3.0)/2))-2);
		int radius = 25;
		int a = (int) (radius * (Math.cos(Math.toRadians(180/6))));
		int s = (int) (a * (2* Math.tan(Math.toRadians(180/6))));
		int hexRows = (900/(radius*2-s/2))-1;
		int hexCols = (1600/(a*2))-1;
		centers = new Vector<Vector<Point>>();
		if(cellType.equals("Q")) {
			finalRows = rows;
			finalCols = cols;
			matrix = ControlPresentacio.getInstance().genSquareMatrix(rows, cols, centers);
		}
		else if(cellType.equals("T")) {
			finalRows = h;
			finalCols = (int)(cols*1.75);
			matrix = ControlPresentacio.getInstance().genTriangleMatrix(h, (int)(cols*1.75), centers);
		}
		else if(cellType.equals("H")) {
			finalRows = hexRows;
			finalCols = hexCols;
			matrix = ControlPresentacio.getInstance().genHexagonMatrix(hexRows, hexCols, centers);
		}
		
	}
	
	public void setBoard(String[][] board) {
		this.board = board;
	}
	
	public void setParams(String[] params) {
		cellType = params[0];
		adjType = params[1];
		finalRows = Integer.parseInt(params[2]);
		finalCols = Integer.parseInt(params[3]);
		this.params = params;
	}
	
	private void setParams() {
		params = new String[] {cellType, adjType, String.valueOf(finalRows), String.valueOf(finalCols)};
	}
	
	
	private void initialize() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
		JComboBox cellTypeBox = new JComboBox();
		cellTypeBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cellTypeBox.setModel(new DefaultComboBoxModel(new String[] {"Triangle", "Square", "Hexagon"}));
		
		JComboBox adjacencyBox = new JComboBox();
		adjacencyBox.setModel(new DefaultComboBoxModel(new String[] {"Borders", "Borders & angles"}));
		adjacencyBox.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel errorMessage = new JLabel("");
		errorMessage.setForeground(Color.RED);
		errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		setCellType(cellTypeBox.getSelectedItem().toString());
		setCellAdjacency(adjacencyBox.getSelectedItem().toString());
		
		
		setMatrix(cellType);
		
        JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g3d) {
                super.paintComponent(g3d);
                Graphics2D g = (Graphics2D) g3d;
        		drawMatrix(matrix, g);
			};
        };
        
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.repaint();
				setMatrix(cellType);
				Graphics2D g = (Graphics2D)panel.getGraphics();
				drawMatrix(matrix, g);
			}
		});
		applyButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton validateButton = new JButton("Validate");
		validateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setParams();
				try {
					validated = ControlPresentacio.getInstance().validateBoard(params, board);	
				} catch (NullPointerException e) {
					validated = false;
					JOptionPane.showMessageDialog(frame, "The hidato is not yet valid, keep trying!", "",  JOptionPane.INFORMATION_MESSAGE);
				}
				validateButton.setEnabled(!validated);
				if(!validated) {
					JOptionPane.showMessageDialog(frame, "The hidato is not yet valid, keep trying!", "",  JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(frame, "Valid", "",  JOptionPane.INFORMATION_MESSAGE);
					for(String[] b : board) {
						for(String ele : b)
							System.out.print(ele);
						System.out.println();
					}
				}
			}
		});
		validateButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(validated) {
					String name = JOptionPane.showInputDialog(frame, "Name your hidato please", null);
					try {
						ControlPresentacio.getInstance().storeHidato(params, board, name);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frame, "An error has  occurred, please try again", "",  JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					String name = JOptionPane.showInputDialog(frame, "Name your board please", null);
					try {
						ControlPresentacio.getInstance().storeBoard(name, params, board);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(frame, "An error has  occurred, please try again", "",  JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
			}
		});
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		DrawView dView = this;
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ListDrawBoards ldBoards = new ListDrawBoards(dView);
					ldBoards.setVisible(true);	
				} catch (NoSuchFileException e) {
					JOptionPane.showMessageDialog(frame, "An error has  occurred, please try again", "",  JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		loadButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                //super.mouseClicked(me);
                Graphics2D g = (Graphics2D)panel.getGraphics();
        		g.setStroke(new BasicStroke(3));
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                for(Integer i = 0; i < matrix.size(); i++) { 
                	for(Integer j = 0; j < matrix.get(0).size(); j++) {
                		Polygon p = matrix.get(i).get(j);
                		if(p.contains(me.getPoint())) {
                			validated = false;
                			validateButton.setEnabled(!validated); 
                			if(board[i][j].equals("#")) {
                				board[i][j] = "?";
                				g.drawPolygon(p);
                			}
                			else if(board[i][j].equals("?")) {
                				if(SwingUtilities.isRightMouseButton(me)) {
                					String num = JOptionPane.showInputDialog(frame, "Introduce a number please:", null);
                					if(!num.equals("") && !isNumeric(num)) {
                						errorMessage.setText("Introduce a valid number please");
                					}
                					else {
                						errorMessage.setText("");
                            			int xOffset = centers.get(i).get(j).x - (4*num.length());
                            			int yOffset = centers.get(i).get(j).y + (2*num.length());
                            			board[i][j] = num; 
                            			g.drawString(num, xOffset, yOffset);
                						
                					}
                				}
                				else {
                					board[i][j] = "*";
                					g.setColor(Color.ORANGE);
                					g.fillPolygon(p);
                					g.setColor(Color.BLACK);
                					g.drawPolygon(p);
                				}
                			}
                			else if(board[i][j].equals("*")) {
                				board[i][j] = "#";
                				g.setColor(Color.WHITE);
                				g.fillPolygon(p);
                				g.setColor(Color.LIGHT_GRAY);
                				g.drawPolygon(p);
                			}
                			else { //numero
                				board[i][j] = "*";
            					g.setColor(Color.ORANGE);
            					g.fillPolygon(p);
            					g.setColor(Color.BLACK);
            					g.drawPolygon(p);
                			}
                		}
                	}
            	}
            }
        };
        
        panel.addMouseListener(ma);//add listener to panel 
        
		panel.setBackground(Color.WHITE);
		
		JLabel celltypeLabel = new JLabel("Cell type");
		celltypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		JLabel adjLabel = 
				new JLabel("Adjacency");
		adjLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		

		

		
		cellTypeBox.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	setCellType(cellTypeBox.getSelectedItem().toString());
                    	setCellAdjacency(adjacencyBox.getSelectedItem().toString());
                    }
                }            
        );
		


		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 828, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1600, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(celltypeLabel)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(cellTypeBox, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(adjLabel)
											.addGap(18)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(adjacencyBox, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
												.addComponent(applyButton))))
									.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(validateButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(saveButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(74)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(197)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(cellTypeBox, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(adjacencyBox, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(celltypeLabel, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
									.addGap(62)
									.addComponent(adjLabel, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
							.addGap(48)
							.addComponent(applyButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
							.addGap(162)
							.addComponent(validateButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		
		
	}
}

