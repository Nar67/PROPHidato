package Domini;
import java.awt.Point;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Persistencia.ControlPersistencia;



public class ControlDomini {
	private Partida currentpartida;
	private Usuari currentuser;
	private static ControlDomini cd = new ControlDomini();
	private ControlDomini() {}
	public static ControlDomini getInstance() {
		return cd;
	}
	
	public String getCurrentUsername() {
		return ControlDomini.getInstance().currentuser.getNom();
	}
	
	public Integer getBoardNumerator() {
		return ControlDomini.getInstance().getBoardNumerator();
	}
	
	private void createCurrentUser(String nom, String pass) {
		this.currentuser = new Usuari(nom,pass);
	}
	
	public String[][] generaHidato(String diff, String cellType, String adj) { 
		Partida partida = new Partida();
		Hidato hidato = partida.generarTaulell(diff, cellType, adj);
		while (!hidato.checkHidato()) {
			hidato = partida.generarTaulell(diff, cellType, adj);
		}
		Partida p = new Partida(hidato);
		this.currentpartida = p;
		p.setUser(this.currentuser);
		p.setDifficulty(diff);
		return hidato.getTaulell().getMatriu();
	}
	
	public Integer nextMove(Integer i, Integer j) throws IOException {
		return this.currentpartida.nextMove(i,j);
	}
	
	public ArrayList<String> getPartides(String user) {
		ControlPersistencia cp = ControlPersistencia.getInstance();
		return cp.getPartides(user);
	}
	
	private String  bytesToHex(byte[] hash) {
        return DatatypeConverter.printHexBinary(hash);
	}
	
	public String getSHA256Hash(String data) {
        String result = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            return bytesToHex(hash); // make it printable
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
	
	public void setInitials(ArrayList<Point> initials) {
		currentpartida.setInitialNums(initials);
	}
	
	public ArrayList<Point> getInitials() {
		return currentpartida.getInitialNums();
	}
	
	public ArrayList<Integer> getHint() {
		int i = currentpartida.getCurri();
		int j = currentpartida.getCurrj();
		return currentpartida.getHidato().nextMove(i, j);
	}
	
	public int getUltim() {
		return currentpartida.getUltim();
	}
	
	public void storeCurrentHidato(String name) throws IOException {
		storeHidato(currentpartida.getHidato().getTaulell().getParams(), currentpartida.getHidato().getTaulell().getMatriu(), name);
	}
	
	public String[] getCurrentParams() {
		return currentpartida.getHidato().getTaulell().getParams();
	}
	
	public String[][] getCurrentBoard() {
		return currentpartida.getHidato().getTaulell().getMatriu();
	}
	
	public int getCurrentTime() {
		return (int)currentpartida.getPausedTime();
	}
	
	public int getCurrentMoves() {
		return (int)currentpartida.getMoves();
	}
	
	public void setCurrentUser(String nom, String password) {
		Usuari u = new Usuari(nom, password);
		this.currentuser = u;
	}
	
	
	
	public ArrayList<Point> getNeighbours(int i, int j, String[] params, String[][] board){
		Board b;
		String par = new String(params[1]);
		params[1] = "C";
		ArrayList<Point> result = new ArrayList<Point>();
		if(params[0].equals("Q")) b = new SquareBoard(params, board);
		else if(params[0].equals("T")) b = new TriangleBoard(params, board);
		else if(params[0].equals("H")) b = new HexagonBoard(params, board);
		else return new ArrayList<Point>();
		ArrayList<Cell> n = b.getNeighbours(b.getCell(i, j));
		for(Cell c : n)
			result.add(new Point(c.getRow(), c.getCol()));
		params[1] = par;
		return result;
			
	}
	
	public void loadPartida(String name) throws IOException {
		ControlPersistencia cp = ControlPersistencia.getInstance();
		String user = currentuser.getNom();
		String partida = cp.loadPartida(user, name);
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		this.currentpartida = gson.fromJson(partida,  Partida.class);
	}
	
	public void savePartida(String name) throws IOException {
		ControlPersistencia cp = ControlPersistencia.getInstance();
		String partida = this.currentpartida.partidaToString();
		String username = this.currentuser.getNom();
		cp.savePartida(partida, username, name);
	}
	
	public void storeHidato(String[] params, String[][] board, String name) throws IOException {
		Board b;
		if(params[0].equals("Q")) b = new SquareBoard(params, board);
		else if(params[0].equals("T")) b = new TriangleBoard(params, board);
		else b = new HexagonBoard(params, board);
		ArrayList<Point> initials = fillInitials(board);
		Hidato h = new Hidato(b, initials);
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		String hidato = gson.toJson(h);
		ControlPersistencia.getInstance().storeHidato(hidato, name);
		
		Gson gsonBoard = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		String boardToStore = gsonBoard.toJson(b);
		ControlPersistencia.getInstance().storeBoard(boardToStore, name);
	}

	public void storeBoard(String name, String[] params, String[][] matrix) throws IOException {
		Board b;
		if(params[0].equals("Q")) 
			b = new SquareBoard(params, matrix);
		else if(params[0].equals("T"))
			b = new TriangleBoard(params, matrix);
		else
			b = new HexagonBoard(params, matrix);
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		String board = gson.toJson(b);
		ControlPersistencia.getInstance().storeBoard(board, name);
	}
	
	
	public void loadBoard(String name,  ArrayList<String> params, ArrayList<ArrayList<String>> matriu) throws IOException {
		String board = ControlPersistencia.getInstance().loadBoard(name);
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "tyCell")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		Board b = gson.fromJson(board, Board.class);
		String[] par = b.getParams();
		String[][] mat = b.getMatriu();
		for(int i = 0; i < par.length; i++)
			params.add(par[i]);
		for(int i = 0; i < mat.length; i++) {
			ArrayList<String> aux = new ArrayList<String>();
			for (int j = 0; j < mat[0].length; j++) {
				aux.add(mat[i][j]);
			}
			matriu.add(aux);
		}
	}
	
	public boolean logInUser(String username, String password) throws IOException {
		if(ControlPersistencia.getInstance().logInUser(username, password)) {
			createCurrentUser(username,password);
			return true;
		}
		return false;
	}
	
	public boolean signUpUser(String username, String password) throws IOException {
		if(ControlPersistencia.getInstance().signUpUser(username, password)) {
			createCurrentUser(username,password);
			return true;
		}
		return false;
	}
	
	public String[] listHidatos() {
		return ControlPersistencia.getInstance().listHidatos();
	}
	
	public String[] listBoards() {
		return ControlPersistencia.getInstance().listBoards();
	}
	
	
	public String[] listGames() {
		return ControlPersistencia.getInstance().listGames(currentuser.getNom());
	}
	
	public HashMap<String, Integer> getEasyRanking() throws IOException {
		String rank = ControlPersistencia.getInstance().getEasyRanking();
		Gson gson = new Gson();
		EasyRanking ranking = gson.fromJson(rank,  EasyRanking.class);
		return ranking.getRanking();
	}
	
	public HashMap<String, Integer> getMediumRanking() throws IOException {
		String rank = ControlPersistencia.getInstance().getMediumRanking();
		Gson gson = new Gson();
		HardRanking ranking = gson.fromJson(rank,  HardRanking.class);
		return ranking.getRanking();
	}
	
	public HashMap<String, Integer> getHardRanking() throws IOException {
		String rank = ControlPersistencia.getInstance().getHardRanking();
		Gson gson = new Gson();
		HardRanking ranking = gson.fromJson(rank,  HardRanking.class);
		return ranking.getRanking();
	}
	
	public void storeEasyRanking(String rts) throws IOException {
		ControlPersistencia.getInstance().storeEasyRanking(rts);
	}
	
	public void storeMediumRanking(String rts) throws IOException {
		ControlPersistencia.getInstance().storeMediumRanking(rts);
	}
	
	public void storeHardRanking(String rts) throws IOException {
		ControlPersistencia.getInstance().storeHardRanking(rts);
	}
	
	public void loadHidato(String name,  ArrayList<String> params, ArrayList<ArrayList<String>> matriu) throws IOException {
		String hidato = ControlPersistencia.getInstance().loadHidato(name);
		RuntimeTypeAdapterFactory<Board> BoardAdapterFactory = RuntimeTypeAdapterFactory.
				of(Board.class, "cellType")
			    .registerSubtype(SquareBoard.class, "Q")
			    .registerSubtype(HexagonBoard.class, "H")
			    .registerSubtype(TriangleBoard.class, "T");
		
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(BoardAdapterFactory).create();
		Hidato h = gson.fromJson(hidato,  Hidato.class);
		currentpartida = new Partida(h);
		currentpartida.setUser(currentuser);
		String[] par = h.getTaulell().getParams();
		String[][] mat = h.getTaulell().getMatriu();
		for(int i = 0; i < par.length; i++)
			params.add(par[i]);
		for(int i = 0; i < mat.length; i++) {
			ArrayList<String> aux = new ArrayList<String>();
			for (int j = 0; j < mat[0].length; j++) {
				aux.add(mat[i][j]);
			}
			matriu.add(aux);
		}
	}
	
	private boolean isNumeric(String s) {
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) return false;
		}
		return true;
	}
	
	private ArrayList<Point> fillInitials(String[][] board){
		ArrayList<Point> result = new ArrayList<Point>();
		for(int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++) {
				if(isNumeric(board[i][j])) result.add(new Point(i, j));
			}
		return result;
	}
	
	public boolean validateBoard(String[] params, String[][] board) {
		Board b;
		if(params[0].equals("Q")) b = new SquareBoard(params, board);
		else if(params[0].equals("T")) b = new TriangleBoard(params, board);
		else b = new HexagonBoard(params, board);
		ArrayList<Point> initials = fillInitials(board);
		Hidato hidato = new Hidato(b, initials);
		boolean a = hidato.checkHidato();
		if(a) {
        	String[][] solvedBoard = hidato.printHidato();
        	for(String[] s : solvedBoard){
        		for(String q : s)
        			if(q.equals("?"))
        				return false;
        	}
		}
        return a;
	}
	
}