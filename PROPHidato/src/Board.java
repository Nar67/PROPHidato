public abstract class Board {
	private String tyAdj;
	private String boardID;
	private Integer rows;
	private Integer cols;
	Cell cells[][];
	
	
	public String getTyAdj() {
		return tyAdj;
	}

	public Board(Integer rows, Integer cols) {
		this.rows = rows;
		this.cols = cols;
		for (Integer i = 0; i < rows; i++) {
			for (Integer j = 0; j < cols; j++) {
				cells[i][j]= new Cell(i, j);
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

	
	void setValueToCell(Integer row, Integer col, Integer value) {
		cells[row][col].setValue(value);
	}
	
	void setValueToCell(Cell cell, Integer value) {
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
