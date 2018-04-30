import java.util.ArrayList;

public class TriangleBoard extends Board {

	public TriangleBoard(String params[], String input[][]) {
		super(params, input);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Cell> getNeighbours(Cell cell) {
		Integer i = cell.getRow();
		Integer j = cell.getCol();
		ArrayList<Cell> result = new ArrayList<Cell>();
		if(i < 1) {
			if(this.getTyAdj().equals("CA")) {
				if(j < 1) {
					for(int q = 0; q < 2; ++q) {
						for(int w = 1; w < 3; ++w) {
							result.add(this.getCell(i+q, j+w));
						}
					}
					result.add(this.getCell(i+1, j));

				}
				else if(j < this.getCols() - 1) {
					if(j%2 == 0) {
						for(int q = 0; q < 2; ++q) {
							for(int w = -2; w < 3; ++w) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols()) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
					}
					else {
						for(int q = 0; q < 2; ++q) {
							for(int w = -1; w < 2; ++w) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols()) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
					}
				}
				else {
					if(j%2 == 0) {
						for(int q = 0; q < 2; ++q) {
							for(int w = -2; w < 0; ++w) {
								result.add(this.getCell(i+q, j+w));
							}
						}
						result.add(this.getCell(i+1, j));
					}
					else {
						result.add(this.getCell(i+1, j));
						result.add(this.getCell(i, j-1));
						result.add(this.getCell(i+1, j-1));

					}
				}
			}
			else {
					if(j < 1) {
						result.add(this.getCell(i, j+1));
						result.add(this.getCell(i+1, j));
					}
					else if(j < this.getCols() - 1) {
						result.add(this.getCell(i, j+1));
						result.add(this.getCell(i, j-1));
						if(j%2 == 0) {
							result.add(this.getCell(i+1, j));
						}
					}
					else {
						result.add(this.getCell(i, j-1));
						if(j%2 == 0) {
							result.add(this.getCell(i+1, j));
						}
					}
			}
		}
		else if(i < this.getRows() - 1) {
			if(this.getTyAdj().equals("CA")) {
				if(i%2 == 0) {
					if(j%2 == 0) {
						for(int q = 0; q < 2; ++q) {
							for(int w = -2; w < 3; ++w ) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols()) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
						for(int q = -1; q < 2; ++q) {
							if((j+q) > -1 && (j+q) < this.getCols()) result.add(this.getCell(i-1, j+q));
						}
					}
					else {
						for(int q = -1; q < 1; ++q) {
							for(int w = -2; w < 3; ++w ) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols()) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
						for(int q = -1; q < 2; ++q) {
							if((j+q) > -1 && (j+q) < this.getCols()) result.add(this.getCell(i+1, j+q));
						}
					}
				}
				else {
					if(j%2 == 0) {
						for(int q = -1; q < 1; ++q) {
							for(int w = -2; w < 3; ++w ) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols()) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
						for(int q = -1; q < 2; ++q) {
							if((j+q) > -1 && (j+q) < this.getCols()) {
								result.add(this.getCell(i+1, j+q));
							}
						}
					}
					else {
						for(int q = 0; q < 2; ++q) {
							for(int w = -2; w < 3; ++w ) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols()) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
						for(int q = -1; q < 2; ++q) {
							if((j+q) > -1 && (j+q) < this.getCols()) result.add(this.getCell(i-1, j+q));
						}
					}
				}
			}
			else {
				if(j < 1) {
					result.add(this.getCell(i, j+1));
					if(i%2 == 0) {
						result.add(this.getCell(i+1, j));
					}
					else {
						result.add(this.getCell(i-1, j));
					}
				}
				else if(j < this.getCols() - 1) {
					result.add(this.getCell(i, j+1));
					result.add(this.getCell(i, j-1));
					if(i%2 == 0) {
						if(j%2 == 0) {
							result.add(this.getCell(i+1, j));
						}
						else {
							result.add(this.getCell(i-1, j));
						}
					}
					else {
						if(j%2 == 0) {
							result.add(this.getCell(i-1, j));
						}
						else {
							result.add(this.getCell(i+1, j));
						}
					}
				}
				else {
					result.add(this.getCell(i, j-1));
					if(i%2 == 0) {
						if(j%2 == 0) {
							result.add(this.getCell(i+1, j));
						}
						else {
							result.add(this.getCell(i-1, j));
						}
					}
					else {
						if(j%2 == 0) {
							result.add(this.getCell(i-1, j));
						}
						else {
							result.add(this.getCell(i+1, j));
						}
					}
				}
			}
			
		}
		else {
			if(this.getTyAdj().equals("CA")) {
				if(i%2 == 0) {
					if(j%2 == 0) {
						for(int q = 0; q < 2; ++q) {
							for(int w = -2; w < 3; ++w ) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols() && (i+q) < this.getRows() && (i+q) > -1) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
					}
					else {
						for(int q = 0; q < 1; ++q) {
							for(int w = -2; w < 3; ++w ) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols() && (i+q) < this.getRows() && (i+q) > -1) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
						for(int q = -1; q < 2; ++q) {
							
							if((j+q) > -1 && (j+q) < this.getCols() && (i+1) < this.getRows()) result.add(this.getCell(i+1, j+q));
						}
					}
				}
				else {
					if(j%2 == 0) {
						for(int q = 0; q < 1; ++q) {
							for(int w = -2; w < 3; ++w ) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols() && (i+q) < this.getRows() && (i+q) > -1) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
						for(int q = -1; q < 2; ++q) {
							if((j+q) > -1 && (j+q) < this.getCols() && (i+1) < this.getRows()) result.add(this.getCell(i+1, j+q));
						}
					}
					else {
						for(int q = 0; q < 2; ++q) {
							for(int w = -2; w < 3; ++w ) {
								if((i+q) != i || (w+j) != j ) {
									if((j+w) >  -1 && (j+w) < this.getCols() && (i+q) < this.getRows() && (i+q) > -1) {
										result.add(this.getCell(i+q, j+w));
									}
								}
							}
						}
					}
				}
			}
			else {
				if(j < 1) {
					result.add(this.getCell(i, j+1));
					if(i%2 != 0) {
						result.add(this.getCell(i-1, j));
					}
				}
				else if(j < this.getCols() - 1) {
					result.add(this.getCell(i, j+1));
					result.add(this.getCell(i, j-1));
					if(i%2 == 0) {
						if(j%2 != 0) {
							result.add(this.getCell(i-1, j));
						}
					}
					else {
						if(j%2 == 0) {
							result.add(this.getCell(i-1, j));	
						}
					}
				}
				else {
					result.add(this.getCell(i, j-1));
					if(i%2 == 0) {
						if(j%2 != 0) {
							result.add(this.getCell(i-1, j));
						}
					}
					else {
						if(j%2 == 0) {
							result.add(this.getCell(i-1, j));
						}
					}
				}
			}
			
		}
		return result;
	}

}
