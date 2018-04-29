import java.util.Scanner;

public class ControlDomini {
	
	public Hidato inicialitzaManualHidato(String[][] matriu, String[] params) {
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
		hidato.checkHidato();
		return hidato;
	}
	
	public Hidato inicialitzaAutoHidato(String[][] matriu, int diff, int randomi, int randomj) {
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
		hidato.checkHidato();
		return hidato;
	}
	
	
	public void jugaManualHidato(String[][] matriu, String[] params) {
		Hidato hidato = inicialitzaManualHidato(matriu, params);
		Partida partida = new Partida(hidato);		
		partida.startPlaying();
	}
	
	public void jugaAutoHidato(String[][] matriu, int diff, int randomi, int randomj) {
		Hidato hidato = inicialitzaAutoHidato(matriu, diff, randomi, randomj);
		Partida partida = new Partida(hidato);
		partida.startPlaying();
	}
}
