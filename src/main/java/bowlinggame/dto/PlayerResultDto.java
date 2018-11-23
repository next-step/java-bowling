package bowlinggame.dto;

import java.util.Collections;
import java.util.List;

public class PlayerResultDto {

	private String name;
	private List<FrameResultDto> totalResult;

	public PlayerResultDto(String name, List<FrameResultDto> totalResult) {
		this.name = name;
		this.totalResult = totalResult;
	}

	public String getName() {
		return name;
	}

	public FrameResultDto getFrameResult(int index) {
		if (index >= totalResult.size()) {
			return new FrameResultDto(Collections.emptyList());
		}
		return totalResult.get(index);
	}
}
