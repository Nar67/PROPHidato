package Tests;

import java.util.Scanner;

import Domini.Usuari;

public class UsuariTest {
	
	public static void testGetUsuari() {
		//S'implementara en la seguent entrega ja que agafa un usuari de la base de dades
		
	}
	
	public static void testPrintUsuari() {
		Usuari nou = new Usuari("Tintin", "milu");
		nou.printUsuari();
	}
	
	public static void testChangePassword() {
		Usuari nou = new Usuari("Tintin", "milu");
		nou.printUsuari();
		System.out.println("Primer intentarem canviar la contrasenya per la mateix, hauria de tornar error");
		nou.ChangePassword("milu");
		System.out.println("Ara canviem per una vàlida");
		nou.ChangePassword("llam");
		nou.printUsuari();
	}
	
	public static void main(String[] args) {
		System.out.println("Per a imprimir un Usuari premi: 2; per a canviar la contrasenya a un Usuari premi: 3");
		Scanner keyboard = new Scanner(System.in);
		int action = keyboard.nextInt();
		while (true) {
			if (action == 2) testPrintUsuari();
			else if (action == 3) testChangePassword();
			else System.out.println("No es una opció vàlida, intenti-ho de nou.");
			System.out.println();
			action = keyboard.nextInt();
		}
	}
}