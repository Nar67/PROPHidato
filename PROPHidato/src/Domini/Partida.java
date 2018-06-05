package Domini;
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
	
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
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
	
	public void initializeContext() {
		this.puntuacio = 0;
		this.difficulty = "Easy";
		this.startime = System.currentTimeMillis();
		this.current = 2;
		this.nmoves = 0;
		this.current = 2;
		this.curri = 0;
		this.currj = 0;
		this.previ = new Integer[64];
		this.prevj = new Integer[64];
	}
	
	public Partida() {
		initializeContext();
	}
	
	public Partida(Hidato hidato) {
		this.hidato = hidato;
		initializeContext();
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
		/*
	public Hidato llegirTaulell() {
		System.out.println("Introdueixi un hidato valid\n");
		Scanner keyboard = new Scanner(System.in);
		keyboard = new Scanner(System.in);
		String dades = keyboard.nextLine();
		String params[] = dades.split(",");
		String matriu[][] = new String[Integer.parseInt(params[2])][Integer.parseInt(params[3])];
		int max = 0;
		for (int i = 0; i < matriu.length; ++i) {
			String aux = keyboard.nextLine();
			String aux2[] = aux.split(",");
			for (int j = 0; j < aux2.length; ++j) {
				matriu[i][j] = aux2[j];
				if (!aux2[j].equals("*") && !aux2[j].equals("?") && !aux2[j].equals("#") && Integer.parseInt(aux2[j]) > max) max = Integer.parseInt(aux2[j]);
			}
		}
		setUltim(max);
		Board tauler;
		switch(params[0]) {
			case "H":
				tauler = new HexagonBoard(params,  matriu);
				break;
			case "T":
				tauler = new TriangleBoard(params,  matriu);
				break;
			default:
				tauler = new SquareBoard(params, matriu);
				break;
		};
		Hidato hidato = new Hidato(tauler);	
		return hidato;
	}
	*/
	public Hidato generarTaulell(String diff, String cellType, String adj) {
		
		int randomi = ThreadLocalRandom.current().nextInt(3, 8);
		int randomj = ThreadLocalRandom.current().nextInt(3, 8);
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
		String cellValue = hidato.getTaulell().getCell(i, j).getValue();
		if(i == -1 && j == -1) {
			pausedTime = (long)((System.currentTimeMillis() - startime)/1000.0);
			
		}
		if (!isAcabada()) {
			/*
			System.out.println("Introdueixi la posicio (i,j) on vol posar el seguent numero");
			System.out.println("Si desitja desfer el moviment introdueixi la mateixa posicio (i,j)");
			System.out.println("Si desitja una pista premi: 9");*/
			if (this.current == 2) {
				int[] start = hidato.getStart();
				curri = start[0];
				currj = start[1];
				previ[current-2] = start[0];
				prevj[current-2] = start[1];
			}/*
			if (i == 9) {
				ArrayList<Integer> pista = hidato.nextMove(curri,  currj); 
				int pistai = pista.get(0);
				int pistaj = pista.get(1);
				System.out.print("Següent moviment: (");
				System.out.print(pistai);
				System.out.print(", ");
				System.out.print(pistaj);
				System.out.println(").");
				hidato.getTaulell().printBoard();
			}*/
			//else
				ArrayList<Cell> neighbours = hidato.getTaulell().getNeighbours(hidato.getTaulell().getCell(i, j));
				for(Cell cell : neighbours)
					if(cell.getValue().equals(String.valueOf(current+1)) && !cell.getValue().equals(String.valueOf(ultim))) {
						hidato.getTaulell().setValueToCell(i, j, Integer.toString(current));
						previ[current-2] = curri;
						prevj[current-2] = currj;
						curri = i;
						currj = j;
						current += 2;
						System.out.println("curri currj value: " + hidato.getTaulell().getCell(curri, currj).getValue());
						System.out.println("current value: " + current);
						System.out.println("i j value: " + cellValue);
						return Integer.parseInt(hidato.getTaulell().getCell(curri, currj).getValue());
					}
				if (!moveInBoard(i, j)) {
					System.out.println("El moviment no es valid");
					hidato.getTaulell().printBoard();
					return -1;
				}
				else if (!cellValue.equals("#") && !cellValue.equals("?") && !cellValue.equals("*")) {
					hidato.getTaulell().setValueToCell(i, j, "?");
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
					previ[current-2] = curri;
					prevj[current-2] = currj;
					curri = i;
					currj = j;
					++current;
					hidato.getTaulell().printBoard();
					++nmoves;
					if(cellValue.equals(String.valueOf(current))) {
						System.out.println("test");
						return nextMove(i, j);
					}
					if (current+1 == getUltim()) {
						//acabarPartida();
						double finaltime = (System.currentTimeMillis() - startime)/1000.0;
						int movescore = 300-(nmoves-current)*5;
						this.puntuacio = 300-(int)finaltime + movescore;						
						System.out.println("Enhorabona!! Has resolt l'Hidato correctament!");
						acabada = true;
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
