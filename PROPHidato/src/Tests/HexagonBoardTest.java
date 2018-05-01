package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Domini.Board;
import Domini.Cell;
import Domini.HexagonBoard;

public class HexagonBoardTest {

	String[][] fillMatrix() {
		String[][] matriu = new String[5][5];
		int count = 0;
		for (int i = 0; i < matriu.length; ++i) {
			for (int j = 0; j < matriu[0].length; ++j) {
				matriu[i][j] = Integer.toString(count); 
				++count;
			}
		}
		return matriu;
	}
	
	@Test
	public void testGetNeighbours() {
		String[] params = {"H", "C", "5", "5"};
		String[][] matriu = fillMatrix();
		Board b = new HexagonBoard(params, matriu);
		Cell c = b.getCell(0, 0);
		ArrayList<Cell> neighbours = b.getNeighbours(c);
		assertTrue(neighbours.contains(b.getCell(0, 1)));
		assertTrue(neighbours.contains(b.getCell(1, 0)));
		c = b.getCell(0, 2);
		neighbours = b.getNeighbours(c);
		assertTrue(neighbours.contains(b.getCell(0, 1)));
		assertTrue(neighbours.contains(b.getCell(0, 3)));
		assertTrue(neighbours.contains(b.getCell(1, 1)));
		assertTrue(neighbours.contains(b.getCell(1, 2)));
		c = b.getCell(0, 4);
		neighbours = b.getNeighbours(c);
		assertTrue(neighbours.contains(b.getCell(0, 3)));
		assertTrue(neighbours.contains(b.getCell(1, 3)));
		assertTrue(neighbours.contains(b.getCell(1, 4)));
		c = b.getCell(2, 0);
		neighbours = b.getNeighbours(c);
		assertTrue(neighbours.contains(b.getCell(1, 0)));
		assertTrue(neighbours.contains(b.getCell(3, 0)));
		assertTrue(neighbours.contains(b.getCell(2, 1)));
		assertFalse(neighbours.contains(b.getCell(1, 2)));
		c = b.getCell(3, 2);
		neighbours = b.getNeighbours(c);
		assertTrue(neighbours.contains(b.getCell(3, 1)));
		assertTrue(neighbours.contains(b.getCell(3, 3)));
		assertTrue(neighbours.contains(b.getCell(2, 2)));
		assertTrue(neighbours.contains(b.getCell(2, 3)));
		assertTrue(neighbours.contains(b.getCell(4, 2)));
		assertTrue(neighbours.contains(b.getCell(4, 3)));
	}
}
