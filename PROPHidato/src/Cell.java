
public class Cell {
	private String value;
	private Integer col;
	private Integer row;
	private String tyCell;
	
	public String getTyCell() {
		return tyCell;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Cell(Integer row, Integer col) {
		this.row = row;
		this.col = col;
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