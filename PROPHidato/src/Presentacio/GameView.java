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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Canvas;

public class GameView {

	private JFrame frame;
	final int BORDER_SIZE = 50;
	private String[][] board;
	private String[] params;
	private Vector<Vector<Polygon>> matrix;
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
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private Vector<Vector<Polygon>> genSquareMatrix(int rows, int cols){
		int xOffset = 800 - (cols*BORDER_SIZE)/2;
		int yOffset = 450 - (rows*BORDER_SIZE)/2;
		Vector<Vector<Polygon>> matrix = new Vector<Vector<Polygon>>();
		for(int i=0;i<rows;i++){
	        Vector<Polygon> r = new Vector<Polygon>();
	        for(int j=0;j<cols;j++){
	        	Polygon p = new Polygon();
	        	p.addPoint(BORDER_SIZE*j + xOffset, BORDER_SIZE*i + yOffset);
	        	p.addPoint(BORDER_SIZE*j + BORDER_SIZE + xOffset, BORDER_SIZE*i + yOffset);
	        	p.addPoint(BORDER_SIZE*j + BORDER_SIZE + xOffset, BORDER_SIZE*i + BORDER_SIZE + yOffset);
	        	p.addPoint(BORDER_SIZE*j + xOffset, BORDER_SIZE*i + BORDER_SIZE + yOffset);
	            r.add(p);
	        }
	        matrix.add(r);
	    }
		return matrix;
	}
	
	private Vector<Vector<Polygon>> genTriangleMatrix(int rows, int cols){
		int xOffset = 800 - (cols*BORDER_SIZE)/2;
		int yOffset = 450 - (rows*BORDER_SIZE)/2;
		int h = (int) (BORDER_SIZE * (Math.sqrt(3.0)/2));
		Vector<Vector<Polygon>> matrix = new Vector<Vector<Polygon>>();
		for(int i=0;i<rows;i++){
	        Vector<Polygon> r = new Vector<Polygon>();
	        for(int j=0;j<cols;j++){
	        	Polygon p = new Polygon();
	        	if(i%2 == 0) { //row is even
	        		if(j%2 == 0) { // cols is even
	        			p.addPoint((j/2 +1)*BORDER_SIZE + xOffset, h * (i+1) + yOffset);
	        			p.addPoint((j/2 +1)*BORDER_SIZE + BORDER_SIZE + xOffset, h * (i+1) + yOffset);
	        			p.addPoint((j/2 +1)*BORDER_SIZE + BORDER_SIZE/2  + xOffset, i * h  + yOffset);
	        		}
	        		else {
	        			p.addPoint((j/2 +1)*BORDER_SIZE + BORDER_SIZE/2  + xOffset, i * h  + yOffset);
	        			p.addPoint((j/2 +1)*BORDER_SIZE + BORDER_SIZE/2  + xOffset + BORDER_SIZE, i * h  + yOffset);
	        			p.addPoint((j/2 +1)*BORDER_SIZE + BORDER_SIZE + xOffset, h * (i+1) + yOffset);
	        		}
	        	}
	        	else {
	        		if(j%2 == 0) { 	        			
	        			p.addPoint(((j/2)*BORDER_SIZE + BORDER_SIZE/2  + xOffset)+ BORDER_SIZE/2, i * h  + yOffset);
	        			p.addPoint(((j/2)*BORDER_SIZE + BORDER_SIZE/2  + xOffset + BORDER_SIZE)+ BORDER_SIZE/2, i * h  + yOffset);
	        			p.addPoint(((j/2)*BORDER_SIZE + BORDER_SIZE + xOffset)+ BORDER_SIZE/2, h * (i+1) + yOffset);
	        		}
	        		else {
	        			p.addPoint(((j/2 +1)*BORDER_SIZE + xOffset)+ BORDER_SIZE/2, h * (i+1) + yOffset);
	        			p.addPoint(((j/2 +1)*BORDER_SIZE + BORDER_SIZE + xOffset)+ BORDER_SIZE/2, h * (i+1) + yOffset);
	        			p.addPoint(((j/2 +1)*BORDER_SIZE + BORDER_SIZE/2  + xOffset)+ BORDER_SIZE/2, i * h  + yOffset);
	        		}
	        	}
	            r.add(p);
	        }
	        matrix.add(r);
	    }
		return matrix;
	}
	
	private Vector<Vector<Polygon>> genHexagonMatrix(int rows, int cols){
		int radius = 25;
		int a = (int) (radius * (Math.cos(Math.toRadians(180/6))));
		int s = (int) (a * (2* Math.tan(Math.toRadians(180/6))));
		int initialX = 800 - (rows * a)/2;
		int initialY = 450 - (cols * a)/2;
		Vector<Vector<Polygon>> matrix = new Vector<Vector<Polygon>>();
		for(int i=0;i<rows;i++){
	        Vector<Polygon> r = new Vector<Polygon>();
	        for(int j=0;j<cols;j++){
	        	int x = initialX + a*2*j;
	        	int y = initialY + (s/2+radius)*i;
	        	if(i%2 != 0) x += a;

	    		Point center = new Point(x,y);
	        	Polygon p = addPoints(center, radius);
		        r.add(p);
	            }
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
	
	private void initialize() {
		frame = new JFrame();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize((int)screenSize.getWidth(),(int)screenSize.getHeight());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for(String s: params)
			System.out.println("Params2: " + s);
		
		if(params[0].equals("Q")) {
			matrix = genSquareMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]));
		}
		else if(params[0].equals("T"))
			matrix = genTriangleMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]));
		else if(params[0].equals("H"))
			matrix = genHexagonMatrix(Integer.parseInt(params[2]),Integer.parseInt(params[3]));
		//matrix = genHexagonMatrix(7, 7);
		
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g3d) {
                super.paintComponent(g3d);
                Graphics2D g = (Graphics2D) g3d;
                g.setColor(Color.BLACK );
                		
        		System.out.println(String.valueOf(matrix.size()));
        		System.out.println(String.valueOf(matrix.get(0).size()));
        		
                for(int i = 0; i < matrix.size(); i++) {
                	for(int j = 0; j < matrix.get(0).size(); j++) {
                		Polygon p = matrix.get(i).get(j);
                		System.out.print(board[i][j]);
                		g.setStroke(new BasicStroke(3));
                		g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
                		if(!board[i][j].equals("#"))
                			g.drawPolygon(p);
                		if(board[i][j].equals("*"))
                			g.fillPolygon(p);
                		if(!board[i][j].equals("#") && !board[i][j].equals("?")) {
                			Rectangle r = p.getBounds();
                			int x = (int)r.getLocation().getX();
                			int y = (int)r.getLocation().getY();
                			g.drawString(board[i][j],x+25, y+25);
                		}
                		
                	}
                System.out.println();
                }
            }
            /*
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(800, 600);
            }
            */
        };
        
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
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1600, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(313, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(74)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 900, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(34, Short.MAX_VALUE))
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
