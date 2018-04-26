public abstract class Board {
	
	
	private String tyAdj;
	private String boardID;
	private Integer rows;
	private Integer cols;
	Cell cells[][];
	
	
	public String getTyAdj() {
		return tyAdj;
	}

	public Board(String params[], String input[][]) {
		this.rows = Integer.parseInt(params[2]);
		this.cols = Integer.parseInt(params[3]);
		this.tyAdj = params[1];
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
	
}
