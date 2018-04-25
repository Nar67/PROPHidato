import java.util.ArrayList;
public abstract class Board {
	ArrayList<Cell> cells;
	private Integer cellNumerator;
	
	
	public Board(Cell firstCell) {
		cells = new ArrayList<Cell>();
		cells.add(0, firstCell);
		cellNumerator = 0;
	}
	
	//static Board autogenerate() {}
	
	//boolean isValid() {}
	
	void addCell(Cell cell) {
		cell.setCellID(cellNumerator++);
	}
	
	void removeCell(Integer id) {}
	
}
