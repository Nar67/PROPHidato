import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.PUBLIC_MEMBER;

public abstract class Board {
	private String tyAdj;
	private String boardID;
	private Integer rows;
	private Integer cols;
	private Cell cells[][];
	
	
	public String getTyAdj() {
		return tyAdj;
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
	
	public void printBoard() {
		Integer rows = this.getRows();
		Integer cols = this.getCols();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(this.getCell(i, j).getValue());
			}
			System.out.println();
		}
	}
	*/

	public Board(String params[], String input[][]) {
		this.rows = Integer.parseInt(params[2]);
		this.cols = Integer.parseInt(params[3]);
		this.tyAdj = params[1];
		cells = new Cell[rows][cols];
		for (Integer i = 0; i < rows; i++) {
			for (Integer j = 0; j < cols; j++) {
				cells[i][j]= new Cell(i, j);
				cells[i][j].setValue(input[i][j]);
			}
		}
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
	
	void removeCell(Integer id) {}

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
	
	//public Board autogenerateBoard()
	
	
}
