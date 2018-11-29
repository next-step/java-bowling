package bowlinggame.dto;

import bowlinggame.domain.frame.FrameResult;
import java.util.Collections;
import java.util.List;

public class PlayerResultDto {

	private String name;
	private List<FrameResult> totalResult;

	public PlayerResultDto(String name, List<FrameResult> totalResult) {
		this.name = name;
		this.totalResult = totalResult;
	}

	public String getName() {
		return name;
	}

	public List<String> getFrameResult(int index) {
		if (index >= totalResult.size()) {
			return Collections.emptyList();
		}
		return totalResult.get(index).getDisplayResults();
	}
}
