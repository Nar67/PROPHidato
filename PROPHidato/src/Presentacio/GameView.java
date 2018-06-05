package Presentacio;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BasicStroke;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GameView {

	private JFrame frame;
	private String[][] board;
	private String[] params;
	private Vector<Vector<Polygon>> matrix;
	private Vector<Vector<Point>> centers;
	private int moves;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameView window = new GameView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Create the application.
	 */
	public GameView(String[] params, String[][] matrix) {
		this.params = params;
		this.board = matrix;
		initialize();
	}
	
	public GameView() {
		initialize();
	}


	
	
	private void initialize() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		centers = new Vector<Vector<Point>>();
		if(params[0].equals("Q"))
			matrix = ControlPresentacio.getInstance().genSquareMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]), centers);
		else if(params[0].equals("T"))
			matrix = ControlPresentacio.getInstance().genTriangleMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]), centers);
		else if(params[0].equals("H"))
			matrix = ControlPresentacio.getInstance().genHexagonMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]), centers);
		
        JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g3d) {
                super.paintComponent(g3d);
                Graphics2D g = (Graphics2D) g3d;
                g.setColor(Color.BLACK);
        		g.setStroke(new BasicStroke(3));
        		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                for(int i = 0; i < matrix.size(); i++) {
                	for(int j = 0; j < matrix.get(0).size(); j++) {
                		Polygon p = matrix.get(i).get(j);
                		if(!board[i][j].equals("#"))
                			g.drawPolygon(p);
                		if(board[i][j].equals("*")) {
                			g.setColor(Color.PINK);
                			g.fillPolygon(p);
                			g.setColor(Color.BLACK);
                		}
                		if(!board[i][j].equals("#") && !board[i][j].equals("?")) {
                			int xOffset = centers.get(i).get(j).x - (3*board[i][j].length());
                			int yOffset = centers.get(i).get(j).y + (4*board[i][j].length());
                			g.drawString(board[i][j], xOffset, yOffset);
                		}
                	}
                }
            }
        };
        
		JLabel movesLabel = new JLabel("");
		movesLabel.setFont(new Font("Tahoma", Font.PLAIN, 29));
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);
                for(Integer i = 0; i < matrix.size(); i++)
                	for(Integer j = 0; j < matrix.get(0).size(); j++) {
                		Polygon p = matrix.get(i).get(j);
                		if (p.contains(me.getPoint())) {
                			Graphics2D g = (Graphics2D)panel.getGraphics();
                    		g.setStroke(new BasicStroke(3));
                    		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                			int xOffset = centers.get(i).get(j).x - (3*board[i][j].length());
                			int yOffset = centers.get(i).get(j).y + (4*board[i][j].length());
                			int next = -3;
							try {
								next = ControlPresentacio.getInstance().nextMove(i, j);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(next > 0) {
								g.drawString(String.valueOf(next), xOffset, yOffset);
								movesLabel.setText(String.valueOf(++moves));
							}
							else if(next == -2) { //partida acabada
								System.out.println("acabada");
								JOptionPane.showMessageDialog(frame, "Contratulations you solved the Hidato. Return to the Main Menu", "sda", JOptionPane.INFORMATION_MESSAGE);
								MainMenu mm = new MainMenu();
								mm.getFrame().setVisible(true);
								frame.dispose();
							}
                		}
                	}
                }
        };
        panel.addMouseListener(ma);//add listener to panel 
        
		panel.setBackground(Color.WHITE);
		
		JLabel lblTime = new JLabel("Time ");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 29));
		
		JLabel chrono = new JLabel("");
		chrono.setFont(new Font("Tahoma", Font.PLAIN, 29));
		
		JLabel lblMoves = new JLabel("Moves");
		lblMoves.setFont(new Font("Tahoma", Font.PLAIN, 29));
		


		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1600, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTime)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(chrono, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMoves, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(movesLabel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(36, Short.MAX_VALUE))
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
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(chrono, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
							.addGap(60)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(movesLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMoves, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(77, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		
	}
	
	
	public void setBoard(String[][] s) {
		this.board = s;
	}
	
	public void setParams(String[] s) {
		 this.params = s;
	}
}
