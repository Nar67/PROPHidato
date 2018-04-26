
public class Partida {
	public Integer dificultat;
	public Hidato hidato;
	
	public Partida createPartida(Integer diff) {
		this.dificultat = diff;
	}
	
	public boolean checkFinished(Hidato hidato) {
		if (hidato.finished) return true;
		return false;
	}
	
	public void nextMove() {
		
	}
}
