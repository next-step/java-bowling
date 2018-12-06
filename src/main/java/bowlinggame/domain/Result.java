package bowlinggame.domain;

import java.util.List;

public class Result {

	private List<PlayerResult> result;

	public Result(List<PlayerResult> result) {
		this.result = result;
	}

	public List<PlayerResult> getResult() {
		return result;
	}
}
