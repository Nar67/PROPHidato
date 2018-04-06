import java.util.Vector;

public class Cell {
	static Integer cellNumerator = 0;
	private Integer cellID;
	private Vector<Cell> neighbours;
	private Integer value;
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Cell() {
		cellID = cellNumerator;
		cellNumerator++;
	}

	public Integer getCellID() {
		return this.cellID;
	}

	public void setCellID(Integer cellID) {
		this.cellID = cellID;
	}

	public Vector<Cell> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(Vector<Cell> neighbours) {
		this.neighbours = neighbours;
	}
}