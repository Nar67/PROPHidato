package Domini;
import java.util.HashMap;

public abstract class Ranking {
	private HashMap<String, Integer> rank;
	public Ranking() {
		this.rank = new HashMap<String, Integer>();
	}
	public Integer getUserScore(String username) {
		return rank.get(username);
	}
	public void setNewScore(String username, Integer score) {
		rank.put(username, score);
	}
}
