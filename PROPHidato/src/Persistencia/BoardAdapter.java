package Persistencia;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import Domini.Board;
import Domini.SquareBoard;

public class BoardAdapter extends TypeAdapter {
	@Override
	public Board read(final JsonReader in) throws IOException {
		in.beginObject();
		while(in.hasNext()) {
			if(in.nextName().equals("tyCell")) {
				switch (in.nextString()) {
				case "Q":
					return new SquareBoard()
					break;

				default:
					break;
				}
			}
		}
	}
}
