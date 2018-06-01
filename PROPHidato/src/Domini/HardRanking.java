package Domini;


public class HardRanking extends Ranking {
	private static HardRanking hr = new HardRanking();
	public static HardRanking getInstance() {
		return hr;
	}
	public HardRanking() {
		super();
	}

	public void setNewScore(String username, Integer score) {
		super.setNewScore(username, score+500);
	}
}
