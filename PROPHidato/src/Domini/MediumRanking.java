package Domini;


public class MediumRanking extends Ranking{
	private static MediumRanking mr = new MediumRanking();
	public static MediumRanking getInstance() {
		return mr;
	}
	public MediumRanking() {
		super();
	}

	public void setNewScore(String username, Integer score) {
		super.setNewScore(username, score+200);
	}
}
