import java.util.ArrayList;

public class Cell {
	private Integer cellID;
	private ArrayList<Cell> neighbours;
	private Integer value;
	private Integer col;
	private Integer row;
	private String tyCell;
	
	public String getTyCell() {
		return tyCell;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Cell(Integer row, Integer col) {
		this.row = row;
		this.col = col;
	}

	public Integer getCellID() {
		return this.cellID;
	}

	public void setCellID(Integer cellID) {
		this.cellID = cellID;
	}

	public ArrayList<Cell> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(ArrayList<Cell> neighbours) {
		this.neighbours = neighbours;
	}

	public Integer getCol() {
		return col;
	}

	public void setCol(Integer col) {
		this.col = col;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}
}