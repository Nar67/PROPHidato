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
    private static int[] given, start;
	
	public Hidato(Board a) {
		this.taulell = a;
		this.solucio = this.taulell;
		this.ctype = a.getCell(0,0).getTyCell();
		this.atype = a.getTyAdj();
		this.rows = a.getRows();
		this.cols = a.getCols();
		this.acabat = false;
	}
	
	public void iniHidato() {
		//inizialització hidato(posar numeros en una board buida)
	}
	
	public void checkHidato() {
		setupHidato();
		boolean aux = solve(start[0],start[1],1,0);
		if(aux) {
			this.acabat = true;
			System.out.println("Hidato correcte, vas per el bon camí ;)");
		}
		else System.out.println("No hi ha solució");
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
        for (int i = 0; i < given.length; i++)
            given[i] = list.get(i);
		
	}
    private  boolean solve(int i, int j, int n, int next) {
		Cell a = this.solucio.getCell(i, j);

    	if (n > given[given.length - 1])
            return true; //ja està resolt
    	if (a.getValue() == "#") return false;
    	if (a.getValue() != "?" && Integer.parseInt(a.getValue()) != n)
            return false;
    	if (a.getValue() == "?" && given[next] == n) 
    		 return false;
    	
    	
    	if(Integer.parseInt(a.getValue()) == n) {
    		++next;
    	}
    	//aqui començem propiament dit a resoldre
    	//posem el valor actual a 0
    	String valor_inicial = a.getValue();
    	this.solucio.setValueToCell(i, j, Integer.toString(n));
    	
    	if(ctype == "Q") {
    		if(atype == "CA") {
    			for (int q = -1; q < 2; q++) {
    				for (int w = -1; w < 2; w++) {
    					if (((i + q >= 0) && (j + w>= 0)) && ((i + q < this.rows) && (j + w< this.cols))&& solve(i + q, j + w, n + 1, next))
                           	return true;
    				}    
    			}
                    
    		}
    		else if(atype == "C") {
    			if(i-1 >= 0) {
    				if(solve(i-1, j, n+1, next)) return true;
    			}
    			if(i+1 < this.rows) {
    				if(solve(i+1, j, n+1, next)) return true;
    			}
    			if(j-1 >= 0) {
    				if(solve(i, j-1, n+1, next)) return true;
    			}
    			if(j+1 < this.cols) {
    				if(solve(i, j+1, n+1, next)) return true;
    			}
    		}
    	}
    	else if(ctype == "T") {
    		if(atype == "CA") {
    			if((i%2 != 0 && j%2 == 0) || (i%2 == 0 && j%2 != 0)) { //triangle dret
    				for(int q = -1; q < 2; ++q) {
    					if(q == -1) {
    						for(int w = -1; w < 2; ++w) {
    							if (((i + q >= 0) && (j + w>= 0)) && ((i + q < this.rows) && (j + w< this.cols))&& solve(i + q, j + w, n + 1, next))
    	                           	return true;
    						}
    					}
    					else {
    						for(int w = -2; w < 4; ++w) {
    							if (((i + q >= 0) && (j + w>= 0)) && ((i + q < this.rows) && (j + w< this.cols))&& solve(i + q, j + w, n + 1, next))
    	                           	return true;
    						}
    					}
    				}
    			}
    			else if((i%2 != 0 && j%2 != 0) || (i%2 == 0 && j%2 == 0)) { //triangle del revés
    				for(int q = -1; q < 2; ++q) {
    					if(q == 1) {
    						for(int w = -1; w < 2; ++w) {
    							if (((i + q >= 0) && (j + w>= 0)) && ((i + q < this.rows) && (j + w< this.cols))&& solve(i + q, j + w, n + 1, next))
    	                           	return true;
    						}
    					}
    					else {
    						for(int w = -2; w < 4; ++w) {
    							if (((i + q >= 0) && (j + w>= 0)) && ((i + q < this.rows) && (j + w< this.cols))&& solve(i + q, j + w, n + 1, next))
    	                           	return true;
    						}
    					}
    				}
    			}
    			
    		}
    		else if(atype == "C") {
    			if((i%2 != 0 && j%2 == 0) || (i%2 == 0 && j%2 != 0)) { //triangle dret
    				if(i+1 < this.rows) {
        				if(solve(i+1, j, n+1, next)) return true;
        			}
        			if(j-1 >= 0) {
        				if(solve(i, j-1, n+1, next)) return true;
        			}
        			if(j+1 < this.cols) {
        				if(solve(i, j+1, n+1, next)) return true;
        			}
    			}
    			else if((i%2 != 0 && j%2 != 0) || (i%2 == 0 && j%2 == 0)) { //triangle del revés
        			if(i+1 < this.rows) {
        				if(solve(i+1, j, n+1, next)) return true;
        			}
        			if(j-1 >= 0) {
        				if(solve(i, j-1, n+1, next)) return true;
        			}
        			if(j+1 < this.cols) {
        				if(solve(i, j+1, n+1, next)) return true;
        			}
    			}
    		}
    		
    	}
    	else if(ctype == "H") {
    		//per el cas dels hexagons observem que l'algorisme per CA i per C és el mateix
    		if(i%2 == 0) {
    			for (int q = -1; q < 2; q++) {
    				if(q == 0) {
    					if(j-1 >= 0) if(solve(i+q, j-1, n+1, next)) return true;
    					if(j+1 < this.cols) if(solve(i+q, j+1, n+1, next)) return true;
    				}
    				else {
    					if ((i + q >= 0) && (i + q < this.rows)) {
    						if(j-1 >= 0) if(solve(i+q, j-1, n+1, next)) return true;
        					if(j < this.cols) if(solve(i+q, j, n+1, next)) return true;
    					}
    				}
    			}
    		}
    		else {
    			for (int q = -1; q < 2; q++) {
    				if(q == 0) {
    					if(j-1 >= 0) if(solve(i+q, j-1, n+1, next)) return true;
    					if(j+1 < this.cols) if(solve(i+q, j+1, n+1, next)) return true;
    				}
    				else {
    					if ((i + q >= 0) && (i + q < this.rows)) {
    						if(j >= 0) if(solve(i+q, j, n+1, next)) return true;
        					if(j +1 < this.cols) if(solve(i+q, j+1, n+1, next)) return true;
    					}
    				}
    			}
    		}
    		
    	}
    	
    	//tornem a posar el valor bo
    	this.solucio.setValueToCell(i, j, valor_inicial);
    	return false;
    }
	public void resoldreHidato() {
		setupHidato();
		boolean aux = solve(start[0],start[1],1,0);
		if(aux) this.acabat = true;
		else System.out.println("No hi ha solució");

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
	}
	
	public Integer nextMove() {
		//retona quina cella és el seguent moviment
		return 0;
	}

}
