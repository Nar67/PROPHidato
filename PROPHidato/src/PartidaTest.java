import java.util.Scanner;


public class PartidaTest {
	
	private Partida generatepartida(){
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
	
	public void testmoveInBoard() {
		Partida partida = generatepartida();
		if (!partida.moveInBoard(6, 5)) System.out.println("El moviment no es valid");
	}

	public void testLlegirTaulell() {
		Partida partida = generatepartida();
		Hidato hidato = partida.llegirTaulell();
		hidato.getTaulell().printBoard();
	}
	
	public void testGenerarTaulell() {
		Partida partida = generatepartida();
		Hidato hidato = partida.generarTaulell(4);
		hidato.getTaulell().printBoard();
	}
	
}
