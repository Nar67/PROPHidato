package Domini;
import java.util.ArrayList;
import java.util.Scanner;


import java.util.concurrent.ThreadLocalRandom;

import org.ietf.jgss.Oid;


public class Partida {
	private Integer ultim;
	private Hidato hidato;
	private boolean acabada = false;
	
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
	}
	
	public Partida() {
		
	}
	
	public Partida(Hidato hidato) {
		this.hidato = hidato;
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
				System.out.print("Següent moviment: (");
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
					if (current == getUltim()) {
						acabarPartida();
						System.out.println("Enhorabona!! Has resolt l'Hidato correctament!");
					}
				}
			}			
		}
	}
	
}
