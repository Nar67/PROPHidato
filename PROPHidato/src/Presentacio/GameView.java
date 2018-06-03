package Presentacio;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.omg.CORBA.PRIVATE_MEMBER;

import javax.swing.Timer;

public class GameView {

	private JFrame frame;
	final int BORDER_SIZE = 50;
	private String[][] board;
	private String[] params;
	private Vector<Vector<Polygon>> matrix;
	Vector<Vector<Point>> centers;
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


	private Point calcCenter(Vector<Point> points) {
	    double centroidX = 0, centroidY = 0;
        for(Point p : points) {
            centroidX += p.getX();
            centroidY += p.getY();
        }
    return new Point((int)centroidX / points.size(),(int) centroidY / points.size());
	}
	
	
	private Vector<Vector<Polygon>> genSquareMatrix(int rows, int cols, Vector<Vector<Point>> centers){
		int xOffset = 800 - (cols*BORDER_SIZE)/2;
		int yOffset = 450 - (rows*BORDER_SIZE)/2;
		Vector<Vector<Polygon>> matrix = new Vector<Vector<Polygon>>();
		for(int i=0;i<rows;i++){
	        Vector<Polygon> r = new Vector<Polygon>();
	        Vector<Point> c = new Vector<Point>();
	        for(int j=0;j<cols;j++){
	        	Polygon p = new Polygon();
	        	Vector<Point> points = new Vector<Point>();
	        	points.add(new Point(BORDER_SIZE*j + xOffset, BORDER_SIZE*i + yOffset));
	        	points.add(new Point(BORDER_SIZE*j + BORDER_SIZE + xOffset, BORDER_SIZE*i + yOffset));
	        	points.add(new Point(BORDER_SIZE*j + BORDER_SIZE + xOffset, BORDER_SIZE*i + BORDER_SIZE + yOffset));
	        	points.add(new Point(BORDER_SIZE*j + xOffset, BORDER_SIZE*i + BORDER_SIZE + yOffset));
	        	p.addPoint(points.get(0).x, points.get(0).y);
	        	p.addPoint(points.get(1).x, points.get(1).y);
	        	p.addPoint(points.get(2).x, points.get(2).y);
	        	p.addPoint(points.get(3).x, points.get(3).y);
	        	c.addElement(calcCenter(points));
	            r.add(p);
	        }
	        centers.add(c);
	        matrix.add(r);
	    }
		return matrix;
	}
	
	private Vector<Vector<Polygon>> genTriangleMatrix(int rows, int cols, Vector<Vector<Point>> centers){
		int xOffset = 800 - (cols*BORDER_SIZE)/2;
		int yOffset = 450 - (rows*BORDER_SIZE)/2;
		int h = (int) (BORDER_SIZE * (Math.sqrt(3.0)/2));
		Vector<Vector<Polygon>> matrix = new Vector<Vector<Polygon>>();
		for(int i=0;i<rows;i++){
	        Vector<Polygon> r = new Vector<Polygon>();
	        Vector<Point> c = new Vector<Point>();
	        for(int j=0;j<cols;j++){
	        	Polygon p = new Polygon();
	        	Vector<Point> points = new Vector<Point>();
	        	if(i%2 == 0) { //row is even
	        		if(j%2 == 0) { // cols is even
	        			points.add(new Point((j/2 +1)*BORDER_SIZE + xOffset, h * (i+1) + yOffset));
	        			points.add(new Point((j/2 +1)*BORDER_SIZE + BORDER_SIZE + xOffset, h * (i+1) + yOffset));
	        			points.add(new Point((j/2 +1)*BORDER_SIZE + BORDER_SIZE/2  + xOffset, i * h  + yOffset));
	        		}
	        		else {
	        			points.add(new Point((j/2 +1)*BORDER_SIZE + BORDER_SIZE/2  + xOffset, i * h  + yOffset));
	        			points.add(new Point((j/2 +1)*BORDER_SIZE + BORDER_SIZE/2  + xOffset + BORDER_SIZE, i * h  + yOffset));
	        			points.add(new Point((j/2 +1)*BORDER_SIZE + BORDER_SIZE + xOffset, h * (i+1) + yOffset));
	        		}
	        	}
	        	else {
	        		if(j%2 == 0) {
	        			points.add(new Point(((j/2)*BORDER_SIZE + BORDER_SIZE/2  + xOffset)+ BORDER_SIZE/2, i * h  + yOffset));
	        			points.add(new Point(((j/2)*BORDER_SIZE + BORDER_SIZE/2  + xOffset + BORDER_SIZE)+ BORDER_SIZE/2, i * h  + yOffset));
	        			points.add(new Point(((j/2)*BORDER_SIZE + BORDER_SIZE + xOffset)+ BORDER_SIZE/2, h * (i+1) + yOffset));
	        		}
	        		else {
	        			points.add(new Point(((j/2 +1)*BORDER_SIZE + xOffset)+ BORDER_SIZE/2, h * (i+1) + yOffset));
	        			points.add(new Point(((j/2 +1)*BORDER_SIZE + BORDER_SIZE + xOffset)+ BORDER_SIZE/2, h * (i+1) + yOffset));
	        			points.add(new Point(((j/2 +1)*BORDER_SIZE + BORDER_SIZE/2  + xOffset)+ BORDER_SIZE/2, i * h  + yOffset));
	        		}
	        	}
	        	p.addPoint(points.get(0).x, points.get(0).y);
	        	p.addPoint(points.get(1).x, points.get(1).y);
	        	p.addPoint(points.get(2).x, points.get(2).y);
	        	c.addElement(calcCenter(points));
	            r.add(p);
	        }
	        centers.add(c);
	        matrix.add(r);
	    }
		return matrix;
	}
	
	private Vector<Vector<Polygon>> genHexagonMatrix(int rows, int cols, Vector<Vector<Point>> centers){
		int radius = 25;
		int a = (int) (radius * (Math.cos(Math.toRadians(180/6))));
		int s = (int) (a * (2* Math.tan(Math.toRadians(180/6))));
		int initialX = 800 - (rows * a)/2;
		int initialY = 450 - (cols * a)/2;
		Vector<Vector<Polygon>> matrix = new Vector<Vector<Polygon>>();
		for(int i=0;i<rows;i++){
	        Vector<Polygon> r = new Vector<Polygon>();
	        Vector<Point> c = new Vector<Point>();
	        for(int j=0;j<cols;j++){
	        	int x = initialX + a*2*j;
	        	int y = initialY + (s/2+radius)*i;
	        	if(i%2 != 0) x += a;

	    		Point center = new Point(x,y);
	        	Polygon p = addPoints(center, radius);
	        	c.add(center);
		        r.add(p);
	            }
	        centers.add(c);
	        matrix.add(r);
	        }
		return matrix;
	}
	
	private Polygon addPoints(Point center, int radius) {
		Polygon p = new Polygon();
    	for (int i = 0; i < 6; i++) {
            int xval = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yval = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            Point point = rotatePoint(new Point(xval, yval), center, 30);
            
            p.addPoint(point.x, point.y);
    	}
    	return p;
	}
	
	public Point rotatePoint(Point pt, Point center, double angleDeg) {
        double angleRad = Math.toRadians(angleDeg);
        double cosThetha = Math.cos(angleRad); 
        double sinThetha = Math.sin(angleRad); 
        double dx = (pt.x - center.x); 
        double dy = (pt.y - center.y); 
 
        int ptX = center.x + (int) (dx * cosThetha - dy * sinThetha);
        int ptY = center.y + (int) (dx * sinThetha + dy * cosThetha);
 
        return new Point(ptX, ptY);
    }
	
    private Point getCenter(Rectangle2D r) {
    	double h = r.getHeight();
    	double w = r.getWidth();
    	return new Point((int) (r.getX() + w/2),(int) (r.getY() + h/2));
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
			matrix = genSquareMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]), centers);
		else if(params[0].equals("T"))
			matrix = genTriangleMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]), centers);
		else if(params[0].equals("H"))
			matrix = genHexagonMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]), centers);
		//matrix = genHexagonMatrix(7, 7);
		
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g3d) {
                super.paintComponent(g3d);
                Graphics2D g = (Graphics2D) g3d;
                g.setColor(Color.BLACK );
                for(int i = 0; i < matrix.size(); i++) {
                	for(int j = 0; j < matrix.get(0).size(); j++) {
                		Polygon p = matrix.get(i).get(j);
                		g.setStroke(new BasicStroke(3));
                		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                		if(!board[i][j].equals("#"))
                			g.drawPolygon(p);
                		if(board[i][j].equals("*"))
                			g.fillPolygon(p);
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
                for(Vector<Polygon> v : matrix)
                	for(Polygon p : v)
                		if (p.contains(me.getPoint())) {
                			Rectangle r = p.getBounds();
                			int x = (int)r.getLocation().getX();
                			int y = (int)r.getLocation().getY();
                			Graphics g = panel.getGraphics();
                			movesLabel.setText(String.valueOf(++moves));
                			//g.drawString("1", x+25, y+25);
                			g.fillPolygon(p);
                }

            }
        };
        panel.addMouseListener(ma);//add listener to panel 
        
        
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++)
          p.addPoint((int) (50 + 50 * Math.cos(i * 2 * Math.PI / 6)),
              (int) (50 + 50 * Math.sin(i * 2 * Math.PI / 6)));
		
		//JPanel panel = new JPanel();
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
