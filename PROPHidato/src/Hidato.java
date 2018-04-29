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
	
	public Hidato(Board a) {
		this.taulell = a;
		this.solucio = this.taulell;
		this.ctype = a.getTyCell();
		this.atype = a.getTyAdj();
		this.rows = a.getRows();
		this.cols = a.getCols();
		this.acabat = false;
		this.te_solu = false;
	}
	
	public void iniHidato() {
		//inizialització hidato(posar numeros en una board buida)
	}
	public Board getTaulell() {
		return this.taulell;
	}
	public boolean checkHidato() {
		setupHidato();
		boolean aux = solve(start[0],start[1],1,0);
		this.acabat = true;
		if(aux) this.te_solu = true;
		else te_solu = false;
		return aux;
	}
	
	private void setupHidato() {
		this.solucio = this.taulell;
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
		if (n > given[given.length - 1])
            return true; //ja està resolt
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

    	printHidato();
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
			System.out.println("Hidato correcte, vas per el bon camí ;)");
			printHidato();
		}
		else {System.out.println("No hi ha solució"); this.te_solu = false;}
	}
	
	public void printHidato() {
		for(int i = 0; i < rows; ++i) {
			for(int j = 0; j < cols; ++j) {
				Cell a = this.solucio.getCell(i, j);
				System.out.print(a.getValue());
				System.out.print(",");
			}
			System.out.println();
		}
		System.out.println();

	}
	public boolean isMoveValid(int i, int j, int ii, int jj) {
		if(this.acabat) {
			Cell a = this.solucio.getCell(i, j);
			ArrayList<Cell> result = this.solucio.getNeighbours(a);
	        for (int p = 0; p < result.size(); p++) {
	        	Cell gr = result.get(p);
	        	if(gr.getRow() == ii && gr.getCol() == jj) {
	        		if(!gr.getValue().equals("#") && !gr.getValue().equals("*")) {
	        			return true;
	        		}
	        	}
	        }
		}
		else {
			checkHidato();
			Cell a = this.solucio.getCell(i, j);
			ArrayList<Cell> result = this.solucio.getNeighbours(a);
	        for (int p = 0; p < result.size(); p++) {
	        	Cell gr = result.get(p);
	        	if(gr.getRow() == ii && gr.getCol() == jj) {
	        		if(!gr.getValue().equals("#") && !gr.getValue().equals("*")) {
	        			return true;
	        		}
	        	}
	        }
		}
		return false;
	}
	public ArrayList<Integer> nextMove(int i, int j) {
		//retona quina cella és el seguent moviment
		ArrayList<Integer> resultt = new ArrayList<Integer>();

		if(this.acabat) {
			Cell a = this.solucio.getCell(i, j);
			ArrayList<Cell> result = this.solucio.getNeighbours(a);
	        for (int p = 0; p < result.size(); p++) {
	        	Cell gr = result.get(p);
	        	if(gr.getValue().equals(a.getValue())) {
	        		resultt.add(gr.getRow());
	        		resultt.add(gr.getCol());
	        		return resultt;
	        	}
	        }
		}
		else {
			checkHidato();
			Cell a = this.solucio.getCell(i, j);
			ArrayList<Cell> result = this.solucio.getNeighbours(a);
	        for (int p = 0; p < result.size(); p++) {
	        	Cell gr = result.get(p);
	        	if(gr.getValue().equals(a.getValue())) {
	        		resultt.add(gr.getRow());
	        		resultt.add(gr.getCol());
	        		return resultt;
	        	}
	        }
		}
		return resultt;
	}

}
