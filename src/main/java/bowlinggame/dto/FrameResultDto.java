package bowlinggame.dto;

import java.util.List;

public class FrameResultDto {

	private List<String> results;

	public FrameResultDto(List<String> results) {
		this.results = results;
	}

	public List<String> getResults() {
		return results;
	}
}
