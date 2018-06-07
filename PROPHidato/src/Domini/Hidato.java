package Domini;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hidato {
	private Board taulell;
	private Board solucio;
	private String ctype; //cell type
	private String atype; //adjecencia type
	private Integer rows;
	private Integer cols;
	public Integer maxCells;
	public Integer emptyCells;
	public Integer fixedCells;
	private boolean acabat;
	private boolean te_solu;
    private static int[] given, start;
    ArrayList<Point> initials;

	public Hidato(Board a) {
		initialize(a);
		fillInitials();
	}
	
	public Hidato(Board a, ArrayList<Point> initials) {
		initialize(a);
		this.initials = initials;
	}
	
	private void initialize(Board a) {
		if(a.getTyCell().equals("Q")) {
			this.taulell = new SquareBoard(a.getParams(),a.getMatriu());	
			this.solucio = new SquareBoard(a.getParams(),a.getMatriu());
		}
		else if(a.getTyCell().equals("T")) {
			this.taulell = new TriangleBoard(a.getParams(),a.getMatriu());	
			this.solucio = new TriangleBoard(a.getParams(),a.getMatriu());
		}
		else if(a.getTyCell().equals("H")) {
			this.taulell = new HexagonBoard(a.getParams(),a.getMatriu());	
			this.solucio = new HexagonBoard(a.getParams(),a.getMatriu());
		}
		this.ctype = a.getTyCell();
		this.atype = a.getTyAdj();
		this.rows = a.getRows();
		this.cols = a.getCols();
		this.acabat = false;
		this.te_solu = false;
	}
	
	private void fillInitials() {
		this.initials = new ArrayList<Point>(); 
		for(int i = 0; i < taulell.getMatriu().length; i++)
			for (int j = 0; j < taulell.getMatriu()[0].length; j++) {
				if(isNumeric(taulell.getMatriu()[i][j])) initials.add(new Point(i, j));
			}
	}
	
	public ArrayList<Point> getInitials() {
		return initials;
	}

	public void setInitials(ArrayList<Point> initials) {
		this.initials = initials;
	}

	public void iniHidato() {
		//inizialització hidato(posar numeros en una board buida)
	}
	public Board getTaulell() {
		return this.taulell;
	}
	
	private boolean isNumeric(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) return false;
		}
		return true;
	}
	
	public int[] getStart() {
		for(int i = 0 ;  i < taulell.getMatriu().length; i++)
			for (int j = 0; j < taulell.getMatriu()[0].length; j++) {
				if(taulell.getMatriu()[i][j].equals("1")) start = new int[]{i,j}; 
			}
		return Hidato.start;
	}
	
	
	public boolean checkHidato() {
		setupHidato();
		if(start == null) {
			return false;
		}
		String[][] auxBoard = new String[this.getTaulell().getMatriu().length][this.getTaulell().getMatriu()[0].length];
		for(int i = 0; i< this.getTaulell().getMatriu().length; i++) {
			for(int j = 0; j < this.getTaulell().getMatriu()[0].length;j++) {
				auxBoard[i][j] = this.getTaulell().getMatriu()[i][j];
			}
		}
		boolean aux = solve(start[0],start[1],1,0);
		this.getTaulell().setMatriu(auxBoard);
		this.acabat = true;
		this.te_solu = aux;
		return aux;
	}
	
	private void setupHidato() {
		List<Integer> list = new ArrayList<>(this.rows * this.cols);
        for(int i = 0; i < this.rows; ++i) {
        	for(int j = 0; j < this.cols; ++j) {
        		Cell a = this.solucio.getCell(i, j);
        		switch(a.getValue()) {
        			case "#":
        				break;
        			case "*":
        				break;
        			case "?":
        				break;
        			default:
        				int val = Integer.parseInt(a.getValue());
        				list.add(val);
        				if(val == 1) start = new int[]{i,j}; 
        		}
        	}
        }
        Collections.sort(list);
        given = new int[list.size()];
        for (int i = 0; i < given.length; i++) {
            given[i] = list.get(i);
        }
        
		
	}
    private  boolean solve(int i, int j, int n, int next) {
		Cell a = this.solucio.getCell(i, j);
		if (n > given[given.length - 1]) {
			return true; //ja està resolt
		}
    	if (a.getValue().equals("*")  || a.getValue().equals("#")) return false;
    	if (!a.getValue().equals("?")  && Integer.parseInt(a.getValue()) != n)
            return false;
    	if (a.getValue().equals("?") && given[next] == n) 
    		 return false;
    	
    	
    	if(!a.getValue().equals("?") && Integer.parseInt(a.getValue()) == n) {
    		++next;
    	}
    	//aqui començem propiament dit a resoldre
    	//posem el valor actual a 0
    	String valor_inicial = a.getValue();
    	this.solucio.setValueToCell(i, j, Integer.toString(n));

    	//printHidato();
		ArrayList<Cell> result = this.solucio.getNeighbours(a);
        for (int p = 0; p < result.size(); p++) {
        	Cell gr = result.get(p);
        	if(solve(gr.getRow(),gr.getCol(),n+1,next)) return true;
        }
    	
    	//tornem a posar el valor bo

    	this.solucio.setValueToCell(i, j, valor_inicial);
    	return false;
    }
	public void resoldreHidato() {
		setupHidato();
		boolean aux = solve(start[0],start[1],1,0);
		this.acabat = true;
		if(aux) {
			this.te_solu = true;
			//printHidato();
		}
		else {System.out.println("No hi ha solució"); this.te_solu = false;}
	}
	
	public String[][] printHidato() {
		String[][] board = new String[rows][cols];
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Cell a = this.solucio.getCell(i, j);
				board[i][j]=  a.getValue();
			}
		}
		return board;
	}
	public void printHidatoOriginal() {
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Cell a = this.taulell.getCell(i, j);
				System.out.print(a.getValue());
				System.out.print(",");
			}
			System.out.println();
		}
		System.out.println();
	}
	public boolean isMoveValid(int i, int j, int clickedi, int clickedj, int current) {
		if(this.acabat) checkHidato();
		Cell a = this.solucio.getCell(i, j);
		ArrayList<Cell> result = this.solucio.getNeighbours(a);
		ArrayList<Cell> neighbours = this.solucio.getNeighbours(this.taulell.getCell(clickedi, clickedj));
		for (int p = 0; p < result.size(); p++) {
			Cell gr = result.get(p);
	        if(gr.getRow() == clickedi && gr.getCol() == clickedj) {
	        	if(!gr.getValue().equals("#") && !gr.getValue().equals("*")) {
	        		return true;
	        	}
	        }
	        else if(neighbours.get(p).getValue().equals(String.valueOf((current-1)))) {
	        	return true;
	        }
		}
		return false;
	}
	public ArrayList<Integer> nextMove(int i, int j){
		//retona quina cella és el seguent moviment
		//assumeix que l'Hidato és resoluble
		ArrayList<Integer> result = new ArrayList<Integer>();
		if(!this.acabat) checkHidato();
		Cell solution = this.solucio.getCell(i, j);
		ArrayList<Cell> neighbours = this.solucio.getNeighbours(solution);
	    for (int p = 0; p < neighbours.size(); p++) {
	    	Cell neighbour = neighbours.get(p);
	    	if(!neighbour.getValue().equals("#") && !neighbour.getValue().equals("*")) {
	    		if(Integer.parseInt(neighbour.getValue()) == ((Integer.parseInt(solution.getValue())) +1)){
	    			if(initials.contains(new Point(neighbour.getRow(), neighbour.getCol())))
	    				return nextMove(neighbour.getRow(), neighbour.getCol());
	    			result.add(neighbour.getRow());
	    			result.add(neighbour.getCol());
	    			return result;
	    		}
	    	}
	    }
	    return result;
	}
}