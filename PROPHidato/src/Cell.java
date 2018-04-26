import java.util.ArrayList;

public class Cell {
	private Integer cellID;
	private ArrayList<Cell> neighbours;
	private Integer value;
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Cell() {
		
	}
	
	public Cell(ArrayList<Cell> neighbours) {
		this.neighbours = neighbours;
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
}