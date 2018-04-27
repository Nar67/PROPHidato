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
		
	}
	
	private void setupHidato() {
		this.solucio = this.taulell;
        List<Integer> list = new ArrayList<>(this.rows * this.cols);
        for(int i = 0; i < this.rows; ++i) {
        	for(int j = 0; j < this.cols; ++j) {
        		Cell a = this.solucio.cells[i][j];
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
		Cell a = this.solucio.cells[i][j];

    	if (n > given[given.length - 1])
            return true; //ja està resolt
    	if (a.getValue() == "?" && Integer.parseInt(a.getValue()) != n)
            return false;
    	if (a.getValue() == "?" && given[next] == n) 
    		 return false;
    	if (Integer.parseInt(a.getValue()) != 0 && given[next] == n)
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
    			
    		}
    		else if(atype == "C") {
    			
    		}
    	}
    	else if(ctype == "T") {
    		if(atype == "CA") {
    			
    		}
    		else if(atype == "C") {
    			
    		}
    		
    	}
    	else if(ctype == "H") {
    		if(atype == "CA") {
    			
    		}
    		else if(atype == "C") {
    			
    		}
    	}
    	
    	//tornem a posar el valor bo
    	this.solucio.setValueToCell(i, j, valor_inicial);
    	
    }
	public void resoldreHidato() {
		setupHidato();
		solve(start[0],start[1],1,0);
	}
	
	public Integer nextMove() {
		//retona quina cella és el seguent moviment
		return 0;
	}

}
