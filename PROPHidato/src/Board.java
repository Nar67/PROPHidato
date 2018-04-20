import java.util.HashMap;
public abstract class Board {
	HashMap<Integer, Cell> cells;
	
	public Board() {
		cells = new HashMap<Integer, Cell>();
	}
	
	public Board(Cell firstCell) {
		cells = new HashMap<Integer, Cell>();
		cells.put(firstCell.getCellID(), firstCell);	
	}
	
	void addCell(Integer id) {
		
		
	}
	
	void removeCell(Integer id) {}
	
	//static Board autogenerate() {}
	
	//boolean isValid() {}
	
}
