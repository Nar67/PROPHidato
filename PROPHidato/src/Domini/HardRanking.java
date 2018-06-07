package Domini;

import java.io.IOException;
import java.util.HashMap;

import com.google.gson.Gson;

public class HardRanking extends Ranking {
	private static HardRanking hr = new HardRanking();
	public static HardRanking getInstance() throws IOException {
		hr.setRanking(ControlDomini.getInstance().getHardRanking());
		return hr;
	}
	public HardRanking() {
		super();
	}

	public void setNewScore(String username, Integer score) throws IOException {
		super.setNewScore(username, score+500);
		Gson gson = new Gson();
		String rts = gson.toJson(this);
		ControlDomini.getInstance().storeHardRanking(rts);
	}	
	public void setRanking(HashMap<String, Integer> ranking) {
		this.rank = ranking;
	}
	
}
