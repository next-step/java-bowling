package bowlinggame.domain;

import bowlinggame.domain.frame.FrameNumber;
import bowlinggame.domain.frame.FrameResult;
import bowlinggame.domain.frame.Score;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerResult {

	private String name;
	private List<FrameResult> frameResults;

	public PlayerResult(String name, List<FrameResult> frameResults) {
		this.name = name;
		this.frameResults = frameResults;
	}

	public String getName() {
		return name;
	}

	public String getRollResult(int index) {
		if (!isFinished(index)) {
			return "";
		}
		return frameResults.get(index).getRollResults();
	}

	public String getScore(int index) {
		if (!isFinished(index)) {
			return Score.EMPTY_SCORE_CHARACTER;
		}
		return frameResults.get(index).getScore();
	}

	public List<String> getRollResults() {
		return IntStream.range(FrameNumber.FIRST - 1, FrameNumber.LAST)
				.mapToObj(index -> getRollResult(index))
				.collect(Collectors.toList());
	}

	public List<String> getScores() {
		return IntStream.range(FrameNumber.FIRST - 1, FrameNumber.LAST)
				.mapToObj(index -> getScore(index))
				.collect(Collectors.toList());
	}

	private boolean isFinished(int index) {
		return index < frameResults.size();
	}
}
