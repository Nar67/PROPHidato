package Domini;
import java.util.ArrayList;
import java.util.Scanner;


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

	public void acabarPartida() {
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
	
	public Partida() {
		this.puntuacio = 0;
		this.difficulty = "Easy";
	}
	
	public Partida(Hidato hidato) {
		this.hidato = hidato;
		this.puntuacio = 0;
	}
	
	public boolean moveInBoard(int i, int j) {
		if (i > hidato.getTaulell().getRows() || i < 0 || j > hidato.getTaulell().getCols() || j < 0) return false;
		return true;
	}
		
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
	
	public Hidato generarTaulell(int diff) {
		//System.out.println("Autogenerando Hidato ...");
		
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
		switch(diff) {
			case 1:
				String params[] = {"Q", "C", Integer.toString(randomi), Integer.toString(randomj)};
				tauler = new SquareBoard(params, matriu);
				break;
			case 2:
				String params1[] = {"Q", "CA", Integer.toString(randomi), Integer.toString(randomj)};
				tauler = new SquareBoard(params1, matriu);
				break;
			case 4:
				String params3[] = {"H", "CA", Integer.toString(randomi), Integer.toString(randomj)};
				tauler = new HexagonBoard(params3,  matriu);
				break;
			case 5:
				String params4[] = {"T", "C", Integer.toString(randomi), Integer.toString(randomj)};
				tauler = new TriangleBoard(params4,  matriu);
				break;
			case 6:	
				String params5[] = {"T", "CA", Integer.toString(randomi), Integer.toString(randomj)};
				tauler = new TriangleBoard(params5,  matriu);
				break;
			default:
				String params2[] = {"H", "C", Integer.toString(randomi), Integer.toString(randomj)};
				tauler = new HexagonBoard(params2,  matriu);
				break;
		}
		Hidato hidato = new Hidato(tauler);
		return hidato;
	}
	
	public void startPlaying() {
		long startime = System.currentTimeMillis();
		int nmoves = 0;
		int current = 2;
		int curri = 0, currj = 0;
		int previ[] = new int[64];
		int prevj[] = new int[64];
		while (!isAcabada()) {
			System.out.println("Introdueixi la posicio (i,j) on vol posar el seguent numero");
			System.out.println("Si desitja desfer el moviment introdueixi la mateixa posicio (i,j)");
			System.out.println("Si desitja una pista premi: 9");
			Scanner keyboard = new Scanner(System.in);
			if (current == 2) {
				int[] start = hidato.getStart();
				curri = start[0];
				currj = start[1];
				previ[current-2] = start[0];
				prevj[current-2] = start[1];
			}
			int i = keyboard.nextInt();
			if (i == 9) {
				ArrayList<Integer> pista = hidato.nextMove(curri,  currj); 
				int pistai = pista.get(0);
				int pistaj = pista.get(1);
				System.out.print("Seg�ent moviment: (");
				System.out.print(pistai);
				System.out.print(", ");
				System.out.print(pistaj);
				System.out.println(").");
				hidato.getTaulell().printBoard();
			}
			else {
				int j = keyboard.nextInt();
				if (!moveInBoard(i, j)) {
					System.out.println("El moviment no es valid");
					hidato.getTaulell().printBoard();
				}
				else if (!hidato.getTaulell().getCell(i, j).getValue().equals("#") && !hidato.getTaulell().getCell(i, j).getValue().equals("?")) {
					hidato.getTaulell().setValueToCell(i, j, "?");
					--current;
					curri = previ[current-2];
					currj = prevj[current-2];
					hidato.getTaulell().printBoard();
					++nmoves;
				}
				else if (!hidato.isMoveValid(curri, currj, i, j)) System.out.println("El moviment no es valid");			
				else {
					hidato.getTaulell().setValueToCell(i, j, Integer.toString(current));
					previ[current-2] = curri;
					prevj[current-2] = currj;
					curri = i;
					currj = j;
					++current;
					hidato.getTaulell().printBoard();
					++nmoves;
					if (current == getUltim()) {
						acabarPartida();
						double finaltime = (System.currentTimeMillis() - startime)/1000.0;
						int movescore = 300-(nmoves-current)*5;
						this.puntuacio = (int)finaltime + movescore;						
						System.out.println("Enhorabona!! Has resolt l'Hidato correctament!");
					}
				}
			}			
		}
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
