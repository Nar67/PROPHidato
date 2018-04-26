import sun.nio.cs.ext.TIS_620;

public class Hidato {
	private Board taulell;
	private String ctype; //cell type
	private String atype; //adjecencia type
	private Integer rows;
	private Integer cols;
	private boolean acabat;
	
	public Hidato(Board a) {
		this.taulell = a;
		this.ctype = a.tyCell();
		this.atype = a.tyAdj();
		this.rows = a.numRows();
		this.cols = a.numCols();
		this.acabat = false;
	}
	
	public void iniHidato() {
		//inizialització hidato(posar numeros en una board buida)
	}
	
	public void checkHidato() {
		
	}
	
	public void resoldreHidato() {
		
	}
	
	public Integer nextMove() {
		//retona quina cella és el seguent moviment
		return 0;
	}

}
