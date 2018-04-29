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

	public Partida(Hidato hidato) {
		
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
	
	public void startPlaying() {
		
		int current = 2;
		while (!isAcabada()) {
			System.out.println("Introduzca la posición (i, j) donde quiere poner el siguiente número");
			Scanner keyboard = new Scanner(System.in);
			int i = keyboard.nextInt();
			int j = keyboard.nextInt();
			if (!isMoveValid(i, j)) System.out.println("El movimiento no es válido");
			else if (!hidato.getTaulell().getCell(i, j).getValue().equals("#") && !hidato.getTaulell().getCell(i, j).getValue().equals("?")) {
				hidato.getTaulell().setValueToCell(Integer.parseInt(i), Integer.parseInt(j), null);
				--current;
				tauler.printBoard();
			}
			else {
				hidato.getTaulell().setValueToCell(Integer.parseInt(i), Integer.parseInt(j), Integer.toString(current));
				++current;
				tauler.printBoard();
			}
		}
	
}
