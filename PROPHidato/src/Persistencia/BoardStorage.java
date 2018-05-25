package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Domini.Board;
import Domini.ControlDomini;
import Domini.HexagonBoard;
import Domini.Partida;
import Domini.SquareBoard;
import Domini.TriangleBoard;

public class BoardStorage {
	
	private static BoardStorage bs = new BoardStorage();
	private BoardStorage() {}
	public static BoardStorage getInstance() {
		return bs;
	}
	
	public void storeBoard(String board, String boardID) throws IOException {
		String path = System.getProperty("user.dir");	
		path = path + File.separator + "Boards";
		File f = new File(path + File.separator + "board" + String.valueOf(new File(path).list().length) + ".txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter writer = new PrintWriter(f);
		writer.write(board);
		writer.close();
		
	}
	public static void main(String[] args) throws IOException {
		BoardStorage bs = BoardStorage.getInstance();
		Board b = bs.readBoard();
		//System.out.println("Board ID 1: " + b.getBoardID());
		Board b2 = bs.readBoard();
		//System.out.println("Board ID 2: " + b2.getBoardID());
		b.storeBoard();
		b2.storeBoard();
	}
	
	public Board readBoard() {
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
		return new SquareBoard(params, matriu);
	}
		/*
			
		byte[] btl = Files.readAllBytes(Paths.get(path + File.separator + "hitler.txt"));
		
		String pene = new String(btl, Charset.forName("UTF-8"));
		Partida partida2 = gson.fromJson(pene, Partida.class);
		partida2.startPlaying();
	}
	*/
}