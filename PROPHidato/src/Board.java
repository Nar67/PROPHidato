import java.util.HashMap;
public abstract class Board {
	HashMap<Integer, Cell> cells;
	
	
	public Board(Cell firstCell) {
		cells = new HashMap<Integer, Cell>();
		cells.put(firstCell.getCellID(), firstCell);
	}
	
	//static Board autogenerate() {}
	
	//boolean isValid() {}
	
	void addCell(Integer id) {}
	
	void removeCell(Integer id) {}
	
}
