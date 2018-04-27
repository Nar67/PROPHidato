import java.util.ArrayList;

public class SquareBoard extends Board {

	public SquareBoard(String params[],String input[][]) {
		super(params, input);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Cell> getNeighbours(Cell cell) {
		Integer i = cell.getRow();
		Integer j = cell.getCol();
		ArrayList<Cell> result = new ArrayList<Cell>();
		if(i < 1) {
			if(j < 1) {
				result.add(this.getCell(i+1, j));
				result.add(this.getCell(i, j+1));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i+1, j+1));
				}
			}
			else if(j < this.getCols() - 1) {
				result.add(this.getCell(i+1, j));
				result.add(this.getCell(i, j+1));
				result.add(this.getCell(i, j-1));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i+1, j+1));
					result.add(this.getCell(i+1, j-1));
				}
			}
			else {
				result.add(this.getCell(i+1, j));
				result.add(this.getCell(i, j-1));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i+1, j-1));
				}
			}
		}
		else if(i < this.getRows() - 1) {
			if(j < 1) {
				result.add(this.getCell(i+1, j));
				result.add(this.getCell(i-1, j));
				result.add(this.getCell(i, j+1));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i+1, j+1));
					result.add(this.getCell(i-1, j+1));
				}
			}
			else if(j < this.getCols() - 1) {
				result.add(this.getCell(i+1, j));
				result.add(this.getCell(i-1, j));
				result.add(this.getCell(i, j-1));
				result.add(this.getCell(i, j+1));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i-1, j-1));
					result.add(this.getCell(i-1, j+1));
					result.add(this.getCell(i+1, j+1));
					result.add(this.getCell(i+1, j-1));
				}
			}
			else {
				result.add(this.getCell(i+1, j));
				result.add(this.getCell(i-1, j));
				result.add(this.getCell(i, j-1));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i-1, j-1));
					result.add(this.getCell(i+1, j-1));
				}
			}
		}
		else {
			if(j < 1) {
				result.add(this.getCell(i-1, j));
				result.add(this.getCell(i, j+1));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i-1, j+1));
				}
			}
			else if(j < this.getCols() - 1) {
				result.add(this.getCell(i, j+1));
				result.add(this.getCell(i, j-1));
				result.add(this.getCell(i-1, j));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i-1, j+1));
					result.add(this.getCell(i-1, j-1));
				}
			}
			else {
				result.add(this.getCell(i, j-1));
				result.add(this.getCell(i-1, j));
				if(this.getTyAdj().equals("CA")) {
					result.add(this.getCell(i-1, j-1));
				}
			}
		}
		return result;
	}

}
