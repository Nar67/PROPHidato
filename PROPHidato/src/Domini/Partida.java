package Domini;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class Partida {
	private Integer ultim;
	private Hidato hidato;
	private Integer partidaID;
	private Usuari user;
	private Integer puntuacio;
	private String difficulty;
	private long startime;
	private long pausedTime; //in seconds
	private Integer nmoves = 0;
	private Integer current = 2;
	private Integer curri = 0;
	private Integer currj = 0;
	private Integer previ[] = new Integer[64];
	private Integer prevj[] = new Integer[64];
	private ArrayList<Point> initialNums;
	
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	public ArrayList<Point> getInitialNums() {
		return initialNums;
	}

	public void setInitialNums(ArrayList<Point> initialNums) {
		this.initialNums = initialNums;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getCurri() {
		return curri;
	}

	public void setCurri(Integer curri) {
		this.curri = curri;
	}

	public Integer getCurrj() {
		return currj;
	}

	public void setCurrj(Integer currj) {
		this.currj = currj;
	}

	public int getMoves() {
		return nmoves;
	}
	
	public long getPausedTime() {
		return pausedTime;
	}

	public void setPausedTime(long pausedTime) {
		this.pausedTime = pausedTime;
	}

	public Integer getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(Integer puntuacio) {
		this.puntuacio = puntuacio;
	}

	public Usuari getUser() {
		return user;
	}

	public void setUser(Usuari user) {
		this.user = user;
	}

	public Integer getPartidaID() {
		return partidaID;
	}

	public void setPartidaID(Integer partidaID) {
		this.partidaID = partidaID;
	}

	private boolean acabada = false;
	
	public Partida getPartida() {
		return this;
	}
	
	public Hidato getHidato() {
		return hidato;
	}

	public void setHidato(Hidato hidato) {
		this.hidato = hidato;
	}
	
	public Integer getUltim() {
		return ultim;
	}

	public void setUltim(Integer ultim) {
		this.ultim = ultim;
	}

	public boolean isAcabada() {
		return acabada;
	}

	public void acabarPartida() throws IOException {
		this.acabada = true;
		Ranking rank;
		int newscore;
		System.out.println("DIFICULTAT:    " + this.difficulty);;
		switch (this.difficulty) {
			case "Medium":
				rank = MediumRanking.getInstance();
				newscore = this.puntuacio + rank.getUserScore(this.user.getNom());
				rank.setNewScore(this.user.getNom(), newscore);
				break;
			
			case "Hard":
				rank = HardRanking.getInstance();
				newscore = this.puntuacio + rank.getUserScore(this.user.getNom());
				rank.setNewScore(this.user.getNom(), newscore);
				break;
	
			default:
				rank = EasyRanking.getInstance();
				int newscor = this.puntuacio + rank.getUserScore(this.user.getNom());
				rank.setNewScore(this.user.getNom(), newscor);
				break;
			}
		
	}
	
	private boolean checkCorrectSolution(int i, int j) {
		if(i == -1 && j == -1) {
			for(Point point : initialNums) {
				//System.out.println("InitialNums: " + hidato.getTaulell().getCell(point.x, point.y).getValue());
				if(hidato.getTaulell().getCell(point.x, point.y).getValue().equals("1")) {
					i = point.x;
					j = point.y;
				}
			}
		}
		Cell cell = hidato.getTaulell().getCell(i, j);
		int cellValue = Integer.parseInt(cell.getValue());
		ArrayList<Cell> neighbours = hidato.getTaulell().getNeighbours(cell);
		for(Cell c : neighbours) {
			System.out.println("cellValue: " + cellValue);
			if((cellValue == ultim-2 && c.getValue().equals("?"))) {
				hidato.getTaulell().setValueToCell(c, String.valueOf(ultim-1));
			}
			System.out.println("neigh: " + c.getValue()); 
			if(c.getValue().equals(String.valueOf(cellValue+1))) {
				if(cellValue+1 == ultim)
					return true;
				else
					return checkCorrectSolution(c.getRow(), c.getCol());
			}
		}
		return false;
	}
	
	public void initializeContext() {
		this.puntuacio = 0;
		this.difficulty = "Easy";
		this.startime = System.currentTimeMillis();
		this.current = 2;
		this.nmoves = 0;
		this.current = 2;
		this.previ = new Integer[64];
		this.prevj = new Integer[64];
	}
	
	public Partida() {
		initializeContext();
	}
	
	public Partida(Hidato hidato) {
		this.hidato = hidato;
		initializeContext();
		initialNums = hidato.getInitials();
		for(Point p : initialNums)
			if(hidato.getTaulell().getMatriu()[p.x][p.y].equals("1")) {
				this.curri = p.x;
				this.currj = p.y;
			}
		int max = 0;
		for(int i = 0; i < hidato.getTaulell().getRows(); i++){
			for (int j = 0; j < hidato.getTaulell().getCols(); j++) {
				if(isNumeric(hidato.getTaulell().getCell(i, j).getValue()) && (Integer.parseInt(hidato.getTaulell().getCell(i, j).getValue())) > max) {
					max = Integer.parseInt(hidato.getTaulell().getCell(i, j).getValue());
				}
			}
		}
		this.ultim = max;
	}
	
	private boolean isNumeric(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) return false;
		}
		return true;
	}
	
	public boolean moveInBoard(int i, int j) {
		if (i > hidato.getTaulell().getRows() || i < 0 || j > hidato.getTaulell().getCols() || j < 0) return false;
		return true;
	}
	
	public Hidato generarTaulell(String diff, String cellType, String adj) {
		int diffdef = 3;
		switch (diff) {
		case "Medium":
			diffdef = 4;
			break;
		case "Hard": 
			diffdef = 5;
			break;
		default:
			break;
		};
		int randomi = ThreadLocalRandom.current().nextInt(diffdef, 8);
		int randomj = ThreadLocalRandom.current().nextInt(diffdef, 8);
		String matriu[][] = new String[randomi][randomj];
		//System.out.println(randomi);
		//System.out.println(randomj);
		int randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
		int randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
		matriu[randomstarti][randomstartj] = "1";
		
		int hashtags = (randomi*randomj)/3;
		for (int i = 0; i < hashtags; ++i) {
			randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
			randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
			while (matriu[randomstarti][randomstartj] != null) {
				randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
				randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
			}
			matriu[randomstarti][randomstartj]= "#"; 
		}
		
		randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
		randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
		while (matriu[randomstarti][randomstartj] != null) {
			randomstarti = ThreadLocalRandom.current().nextInt(0, randomi);
			randomstartj = ThreadLocalRandom.current().nextInt(0, randomj);
		}
		matriu[randomstarti][randomstartj] = Integer.toString((randomi*randomj)-hashtags);
		setUltim((randomi*randomj)-hashtags);

		for (int i = 0; i < matriu.length; ++i) {
			for (int j = 0; j < matriu[0].length; ++j) {
				if ((i != randomstarti || j != randomstartj) && matriu[i][j] == null) matriu[i][j] = "?";					
			}
		}
			
		Board tauler;		

		if (cellType.equals("Square") && adj.equals("Borders")) {
			String params[] = {"Q", "C", Integer.toString(randomi), Integer.toString(randomj)};
			tauler = new SquareBoard(params, matriu);
		}
		else if (cellType.equals("Square") && adj.equals("Borders and angles")) {
			String params1[] = {"Q", "CA", Integer.toString(randomi), Integer.toString(randomj)};
			tauler = new SquareBoard(params1, matriu);
		}
		else if (cellType.equals("Triangle") && adj.equals("Borders")) {
			String params4[] = {"T", "C", Integer.toString(randomi), Integer.toString(randomj)};
			tauler = new TriangleBoard(params4,  matriu);
		}
		else if (cellType.equals("Triangle") && adj.equals("Borders and angles")) {
			String params5[] = {"T", "CA", Integer.toString(randomi), Integer.toString(randomj)};
			tauler = new TriangleBoard(params5,  matriu);
		}
		else {
			String params2[] = {"H", "C", Integer.toString(randomi), Integer.toString(randomj)};
			tauler = new HexagonBoard(params2,  matriu);
		}
		
		Hidato hidato = new Hidato(tauler);
		return hidato;
	}
	
	public Integer nextMove(Integer i, Integer j) throws IOException {
		if(i == -1) {
			pausedTime = j;
			return -3;
		}
		String cellValue = hidato.getTaulell().getCell(i, j).getValue();
		if (!isAcabada()) {
			if (this.current == 2) {
				int[] start = hidato.getStart();
				curri = start[0];
				currj = start[1];
				previ[current-2] = start[0];
				prevj[current-2] = start[1];
			}
				ArrayList<Cell> neighbours = hidato.getTaulell().getNeighbours(hidato.getTaulell().getCell(i, j));
				for(Cell cell : neighbours)
					if(cell.getValue().equals(String.valueOf(current+1)) && !cell.getValue().equals(String.valueOf(ultim))) {
						hidato.getTaulell().setValueToCell(i, j, Integer.toString(current));
						cellValue = Integer.toString(current);
						previ[current-2] = curri;
						prevj[current-2] = currj;
						curri = i;
						currj = j;
						current += 2;
						return Integer.parseInt(hidato.getTaulell().getCell(curri, currj).getValue());
					}
				if (!moveInBoard(i, j)) {
					System.out.println("El moviment no es valid");
					hidato.getTaulell().printBoard();
					return -1;
				}
				else if (!cellValue.equals("#") && !cellValue.equals("?") && !cellValue.equals("*") && !initialNums.contains(new Point(i, j))) {
					hidato.getTaulell().setValueToCell(i, j, "?");
					cellValue = "?";
					--current;
					curri = previ[current-2];
					currj = prevj[current-2];
					hidato.getTaulell().printBoard();
					++nmoves;
					return this.current;
				}
				else if (!hidato.isMoveValid(curri, currj, i, j, current)) {
					System.out.println("El moviment no es valid");
					return -1;
				}
				else {
					hidato.getTaulell().setValueToCell(i, j, Integer.toString(current));
					cellValue = Integer.toString(current);
					previ[current-2] = curri;
					prevj[current-2] = currj;
					curri = i;
					currj = j;
					++current;
					++nmoves;
					if(cellValue.equals(String.valueOf(current))) {
						return nextMove(i, j);
					}
					if (current == getUltim()) {
						// hidato.getTaulell().setValueToCell(i, j, Integer.toString(current));
						System.out.println("test");
						if(checkCorrectSolution(-1, -1)) {
							double finaltime = (System.currentTimeMillis() - startime)/1000.0;
							System.out.println("FINALTIME = " + finaltime);
							System.out.println("Current moves = " + nmoves);
							int movescore = 300-(nmoves)*5;
							int finalscore = movescore - (int)finaltime;
							if (finalscore < 0) finalscore = 0;
							this.puntuacio = finalscore;
							acabarPartida();													
							System.out.println("Enhorabona!! Has resolt l'Hidato correctament!");
							acabada = true;
							return -2;
						}
					}
					return this.current-1;
				}
			//}			
		}
		return -2;
	}
	
	public String partidaToString() {
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		String pts = gson.toJson(this);
		return pts;
	}
}