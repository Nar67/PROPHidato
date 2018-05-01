package Domini;
import java.util.ArrayList;
import java.util.Scanner;

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

	
	/**
	
	public String[] readParams() {
		Scanner keyboard = new Scanner(System.in);
		keyboard = new Scanner(System.in);
		String dades = keyboard.nextLine();
		String res[] = dades.split(",");
		return res;	
	}
	public String[][] readHidato(Integer rows, Integer cols){
		Scanner keyboard = new Scanner(System.in);
		keyboard = new Scanner(System.in);
		String matriu[][] = new String[rows][cols];
		for (int i = 0; i < matriu.length; ++i) {
			String aux = keyboard.nextLine();
			String aux2[] = aux.split(",");
			for (int j = 0; j < aux2.length; ++j) {
				matriu[i][j] = aux2[j]; 
			}
		}
		return matriu;
		
	}
	
	public static void main(String[] args) {
		String params[] = readParams();
		Board board = new SquareBoard(params, matriu);
		board.printBoard();
		Cell cell = board.getCell(2, 3);
		ArrayList<Cell> arrayList = board.getNeighbours(cell);
		for (int i = 0; i < arrayList.size(); i++) {
			Cell c = arrayList.get(i);
			System.out.print("i: " + c.getRow() + "\n");
			System.out.print("j: " + c.getCol() + "\n");
			System.out.print("value: " + c.getValue() + "\n");
			System.out.println(); System.out.println();
		}
	}
}
	*/
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
	/*
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Introduzca un hidato v�lido\n");
		String dades = keyboard.nextLine();
		String params[] = dades.split(",");
		String matriu[][] = new String[Integer.parseInt(params[2])][Integer.parseInt(params[3])];
		for (int i = 0; i < matriu.length; ++i) {
			String aux = keyboard.nextLine();
			String aux2[] = aux.split(",");
			for (int j = 0; j < aux2.length; ++j) {
				matriu[i][j] = aux2[j]; 
				//System.out.print(matriu[i][j]);
			}				
			System.out.println();
		}
		keyboard.close();
		Board board = new TriangleBoard(params, matriu);
		//board.printBoard();
		Cell cell = board.getCell(0, 1);
		ArrayList<Cell> arrayList = board.getNeighbours(cell);
		for (int i = 0; i < arrayList.size(); i++) {
			Cell c = arrayList.get(i);
			System.out.print("i: " + c.getRow() + "\n");
			System.out.print("j: " + c.getCol() + "\n");
			System.out.print("value: " + c.getValue() + "\n");
			System.out.println(); System.out.println();
		}
	}*/

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
	
	abstract ArrayList<Cell> getNeighbours(Cell cell);
}
