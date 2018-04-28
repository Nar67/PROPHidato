import java.util.Scanner;

import com.sun.beans.editors.IntegerEditor;
import com.sun.org.apache.xml.internal.serializer.ElemDesc;
import com.sun.org.apache.xpath.internal.operations.And;
import com.sun.org.apache.xpath.internal.operations.Equals;

import java.util.concurrent.ThreadLocalRandom;


public class Partida {
	private Integer dificultat;
	private Hidato hidato;
	private boolean acabada = false;
	
	public Hidato getHidato() {
		return hidato;
	}

	public void setHidato(Hidato hidato) {
		this.hidato = hidato;
	}

	public boolean isAcabada() {
		return acabada;
	}

	public Partida(Integer diff) {
		
	}
	
	public boolean checkFinished(Hidato hidato) {
		if (hidato.finished) return true;
		return false;
	}
	
	public boolean partidaAcabada() {
		return this.acabada;
	}
	
	public void acabarPartida() {
		this.acabada = true;
	}
	
	public void hint() {
		int cellID = hidato.nextMove();		
	}
	
	public static void main(String[] args) {
		System.out.println("Para autogenerar un Hidato pulse: 1; para generar su propio Hidato pulse: 2.\n");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		if (action == 2) {
			System.out.println("Introduzca un hidato válido\n");
			keyboard = new Scanner(System.in);
			String dades = keyboard.nextLine();
			String params[] = dades.split(",");
			String matriu[][] = new String[Integer.parseInt(params[2])][Integer.parseInt(params[3])];
			for (int i = 0; i < matriu.length; ++i) {
				String aux = keyboard.nextLine();
				String aux2[] = aux.split(",");
				for (int j = 0; j < aux2.length; ++j) {
					matriu[i][j] = aux2[j]; 
					System.out.print(matriu[i][j]);
				}				
				System.out.println();
			}
			Board tauler;
			switch(params[0]) {
				default:
					tauler = new SquareBoard(params, matriu);
					break;
				case "H":
					tauler = new HexagonBoard(params,  matriu);
					break;
				case "T":
					tauler = new TriangleBoard(params,  matriu);
					break;
			};
			Hidato hidato = new Hidato(tauler);
						
			if (hidato.checkHidato()) System.out.println("El tablero introducido no es resoluble");
			else {
				System.out.println("Escoja dificultad: 1(fácil), 2(normal), 3(difícil).");
				int diff = keyboard.nextInt();
				Partida partida;
				partida = new Partida(diff);
				int current = 2;
				while (!partida.isAcabada()) {
					String move = keyboard.nextLine();
					String movepro[] = move.split(" ");
					if (!isMoveValid(movepro[0], movepro[1])) System.out.println("El movimiento no es válido");
					else if (jaExisteix(movepro[0], movepro[1])) {
						tauler.setValueToCell(Integer.parseInt(movepro[0]), Integer.parseInt(movepro[1]), null);
						--current;
					}
					else {
						tauler.setValueToCell(Integer.parseInt(movepro[0]), Integer.parseInt(movepro[1]), Integer.toString(current));
						++current;
					}
				}
			}
		}
		else if (action == 1) {
			System.out.println("Escoja dificultad: 1(muy fácil), 2(fácil), 3(normal), 4(difícil), 5(muy difícil), 6(experto).");
			keyboard = new Scanner(System.in);
			int diff = keyboard.nextInt();
			System.out.println();
			System.out.println("Autogenerando Hidato ...");
			
			int randomi = ThreadLocalRandom.current().nextInt(3, 8);
			int randomj = ThreadLocalRandom.current().nextInt(3, 8);
			String matriu[][] = new String[randomi][randomj];
			System.out.println(randomi);
			System.out.println(randomj);
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

			for (int i = 0; i < matriu.length; ++i) {
				for (int j = 0; j < matriu[0].length; ++j) {
					if ((i != randomstarti || j != randomstartj) && matriu[i][j] == null) matriu[i][j] = "?";					
				}
			}
			
			for (int i = 0; i < matriu.length; ++i) {
				for (int j = 0; j < matriu[0].length; ++j) {
					System.out.print(matriu[i][j]);
					System.out.print(" ");
				}
				System.out.println();
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
				default:
					String params2[] = {"H", "C", Integer.toString(randomi), Integer.toString(randomj)};
					tauler = new HexagonBoard(params2,  matriu);
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
			}
			Hidato hidato = new Hidato(tauler);
			hidato.checkHidato();
		}
    }
	
}
