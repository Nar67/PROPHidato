import java.util.ArrayList;
public abstract class Board {
	ArrayList<Cell> cells;
	private Integer cellNumerator;
	
	public Board() {
		cells = new ArrayList<Cell>();
		cellNumerator = 0;
	}
	
	public Board(Cell firstCell) {
		cells = new ArrayList<Cell>();
		cells.add(0, firstCell);
		cellNumerator = 0;
	}
	
	void addCell(Integer id) {
		
		
	}
	
	void removeCell(Integer id) {}
	
	void addCell(Cell cell) {
		cell.setCellID(cellNumerator++);
	}
	
	//boolean isValid() {}
	
}
