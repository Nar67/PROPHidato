import java.util.Vector;

public class Cell {
	static Integer cellNumerator = 0;
	private Integer cellID;
	private Vector<Cell> neighbours;
	private cellType cellType;
	
	public enum cellType {
		TRIANGLE,
		SQUARE,
		HEXAGON
	}
	
	public Cell(cellType cType) {
		this.setCellType(cType);
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

	public cellType getCellType() {
		return cellType;
	}

	public void setCellType(cellType cellType) {
		this.cellType = cellType;
	}
}