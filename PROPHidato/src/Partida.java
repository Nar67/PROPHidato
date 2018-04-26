import java.util.Scanner;


public class Partida {
	public Integer dificultat;
	public Hidato hidato;
	
	public Partida createPartida(Integer diff) {
		System.out.println("Para autogenerar un Hidato pulse: 1; para generar su propio Hidato pulse: 2.\n");
		if 
	}
	
	public boolean checkFinished(Hidato hidato) {
		if (hidato.finished) return true;
		return false;
	}
	
	public void hint() {
		int cellID = hidato.nextMove();		
	}
	
	
}
