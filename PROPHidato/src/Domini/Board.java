package Domini;
import java.util.ArrayList;

public abstract class Board {
	private String tyAdj;
	private String boardID;
	private Integer rows;
	private Integer cols;
	private String tyCell;
	private Cell cells[][];
	private String[] params;
	private String[][] matriu;
	
	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String[][] getMatriu() {
		return matriu;
	}

	public void setMatriu(String[][] matriu) {
		this.matriu = matriu;
	}

	public void printBoard() {
		Integer rows = this.getRows();
		Integer cols = this.getCols();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if(j != 0){
					System.out.print(",");
				}
				System.out.print(this.getCell(i, j).getValue());
			}
			System.out.println();
		}
	}

	public Board(String params[], String input[][]) {
		this.params = params;
		this.matriu = input;
		this.rows = Integer.parseInt(params[2]);
		this.cols = Integer.parseInt(params[3]);
		this.tyAdj = params[1];
		this.tyCell = params[0];
		cells = new Cell[rows][cols];
		for (Integer i = 0; i < rows; i++) {
			for (Integer j = 0; j < cols; j++) {
				cells[i][j]= new Cell(i, j);
				cells[i][j].setValue(input[i][j]);
			}
		}
	}
	

	
	public String getTyAdj() {
		return tyAdj;
	}
	
	public String getTyCell() {
		return tyCell;
	}

	public void setTyCell(String tyCell) {
		this.tyCell = tyCell;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getCols() {
		return cols;
	}

	public void setCols(Integer cols) {
		this.cols = cols;
	}

	
	void setValueToCell(Integer row, Integer col, String value) {
		cells[row][col].setValue(value);
	}
	
	void setValueToCell(Cell cell, String value) {
		cells[cell.getRow()][cell.getCol()].setValue(value);
	}
	
	public Cell[][] getCells() {
		return cells;
	}

	public String getBoardID() {
		return boardID;
	}

	public void setBoardID(String boardID) {
		this.boardID = boardID;
	}
	
	public Cell getCell(Integer i, Integer j) {
		return cells[i][j];
	}
	
	abstract public ArrayList<Cell> getNeighbours(Cell cell);
}
