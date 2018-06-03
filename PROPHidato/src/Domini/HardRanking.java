package Domini;

import java.io.IOException;

import com.google.gson.Gson;

public class HardRanking extends Ranking {
	private static HardRanking hr = new HardRanking();
	public static HardRanking getInstance() {
		return hr;
	}
	public HardRanking() {
		super();
	}

	public void setNewScore(String username, Integer score) throws IOException {
		super.setNewScore(username, score+500);
		Gson gson = new Gson();
		String rts = gson.toJson(rank, Ranking.class);
		ControlDomini.getInstance().storeHardRanking(rts);
	}
	
}
