import java.util.Scanner;


public class PartidaTest {
	
	private static Partida generatepartida(){
		String[][] matriu = new String[5][5];
		int count = 0;
		for (int i = 0; i < matriu.length; ++i) {
			for (int j = 0; j < matriu[0].length; ++j)
				matriu[i][j] = Integer.toString(count); 
				++count;
		}
		String[] params = {"Q", "CA", "5", "5"};
		Board tauler = new SquareBoard(params, matriu);
		Hidato hidato = new Hidato(tauler);
		Partida partida = new Partida(hidato);
		return partida;
	}
	
	public static void testmoveInBoard() {
		Partida partida = generatepartida();
		if (!partida.moveInBoard(6, 5)) System.out.println("El moviment no es valid");
	}

	public static void testLlegirTaulell() {
		Partida partida = generatepartida();
		Hidato hidato = partida.llegirTaulell();
		hidato.getTaulell().printBoard();
	}
	
	public static void testGenerarTaulell() {
		Partida partida = generatepartida();
		Hidato hidato = partida.generarTaulell(4);
		hidato.getTaulell().printBoard();
	}
	
	public static void main(String[] args) {
		System.out.println("Para autogenerar un Hidato pulse: 1; para generar su propio Hidato pulse: 2.\n");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		while (action != 1 && action != 2) {
			System.out.println("No es una opció vàlida, intenti-ho de nou.");
			action = keyboard.nextInt();
		}
		if (action == 1) testGenerarTaulell();
		else if (action == 2) testLlegirTaulell();;
	}
	
}
