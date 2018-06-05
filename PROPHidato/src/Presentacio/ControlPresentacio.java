package Presentacio;

import java.awt.Point;
import java.awt.Polygon;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import Domini.ControlDomini;

public class ControlPresentacio {
	final int BORDER_SIZE = 50;
	private static ControlPresentacio cp = new ControlPresentacio();
	private ControlPresentacio() {}
	public static ControlPresentacio getInstance() {
		return cp;
	}
	/*
	public void jugar(Integer action) {
		ControlDomini controlador = ControlDomini.getInstance();
		controlador.jugar(action);
 	}*/
	
	public void loadPartida(String user, String name) throws IOException {
		ControlDomini controlDomini = ControlDomini.getInstance();
		controlDomini.loadPartida(user, name);
	}
	
	public void savePartida(String name) throws IOException {
		ControlDomini controlDomini = ControlDomini.getInstance();
		controlDomini.savePartida(name);
	}
	
	public boolean logInUser(String username, char[] password) throws IOException {
		return ControlDomini.getInstance().logInUser(username, ControlDomini.getInstance().getSHA256Hash(String.valueOf(password)));
	}
	
	public boolean signUpUser(String username, char[] password) throws IOException {
		return ControlDomini.getInstance().signUpUser(username, ControlDomini.getInstance().getSHA256Hash(String.valueOf(password)));
	}
	
	public String[][] generateGame(String diff, String cellType, String adj) {
		return ControlDomini.getInstance().generaHidato(diff, cellType, adj);
	}
	
	public String[] listHidatos() {
		return ControlDomini.getInstance().listHidatos();
	}
	
	public String[] listBoards() {
		return ControlDomini.getInstance().listBoards();
	}
	
	public String[] listGames() {
		return ControlDomini.getInstance().listGames();
	}
	
	public HashMap<String, Integer> getEasyRanking() throws IOException {
		return ControlDomini.getInstance().getEasyRanking();
	}
	
	public HashMap<String, Integer> getMediumRanking() throws IOException {
		return ControlDomini.getInstance().getMediumRanking();
	}
	
	public HashMap<String, Integer> getHardRanking() throws IOException {
		return ControlDomini.getInstance().getHardRanking();
	}
	
	public void storeEasyRanking(String rts) throws IOException {
		ControlDomini.getInstance().storeEasyRanking(rts);
	}
	
	public void storeMediumRanking(String rts) throws IOException {
		ControlDomini.getInstance().storeEasyRanking(rts);
	}
	
	public void storeHardRanking(String rts) throws IOException {
		ControlDomini.getInstance().storeEasyRanking(rts);
	}
	
	public void loadHidato(String name, ArrayList<String> params, ArrayList<ArrayList<String>> matriu) throws IOException {
		ControlDomini.getInstance().loadHidato(name, params, matriu);
	}
	
	public void loadBoard(String name, ArrayList<String> params, ArrayList<ArrayList<String>> matriu) throws IOException {
		ControlDomini.getInstance().loadBoard(name, params, matriu);
	}
	
	public void storeBoard(String name, String[] params, String[][] matrix) throws IOException {
		ControlDomini.getInstance().storeBoard(name, params, matrix);
	}
	
	public void storeHidato(String[] params, String[][] board, String name) throws IOException {
		ControlDomini.getInstance().storeHidato(params, board, name);
	}
	
	
	public Integer nextMove(Integer i, Integer j) throws IOException {
		return ControlDomini.getInstance().nextMove(i, j);
	}
	
	private Point calcCenter(Vector<Point> points) {
	    double centroidX = 0, centroidY = 0;
        for(Point p : points) {
            centroidX += p.getX();
            centroidY += p.getY();
        }
    return new Point((int)centroidX / points.size(),(int) centroidY / points.size());
	}
	
	
	public Vector<Vector<Polygon>> genSquareMatrix(int rows, int cols, Vector<Vector<Point>> centers){
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
	
	public Vector<Vector<Polygon>> genTriangleMatrix(int rows, int cols, Vector<Vector<Point>> centers){
		int h = (int) (BORDER_SIZE * (Math.sqrt(3.0)/2));
		int xOffset = 800 - ((cols*BORDER_SIZE)/2)/2;
		int yOffset = 450 - (rows*h)/2;
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
	
	public Vector<Vector<Polygon>> genHexagonMatrix(int rows, int cols, Vector<Vector<Point>> centers){
		int radius = 25;
		int a = (int) (radius * (Math.cos(Math.toRadians(180/6))));
		int s = (int) (a * (2* Math.tan(Math.toRadians(180/6))));
		int initialX = 800 - (cols * (a*2))/2;
		int initialY = 450 - (rows * (radius*2 - s/2))/2;
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
	
	
	public boolean validateBoard(String[] params, String[][] board) {
		return ControlDomini.getInstance().validateBoard(params, board);
	}
	
	
    
}
