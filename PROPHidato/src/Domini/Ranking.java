package Domini;
import java.io.IOException;
import java.util.HashMap;


public abstract class Ranking {
	protected HashMap<String, Integer> rank;
	public Ranking() {
		this.rank = new HashMap<String, Integer>();
	}
	public Integer getUserScore(String username) {
		return rank.get(username);
	}
	public void setNewScore(String username, Integer score) throws IOException {
		rank.put(username, score);
	}
	public HashMap<String, Integer> getRanking(){
		return this.rank;
	}
}
