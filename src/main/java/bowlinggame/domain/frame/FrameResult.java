package bowlinggame.domain.frame;

import bowlinggame.domain.frame.result.Score;
import java.util.List;

public class FrameResult {

	private List<String> results;
	private Score score;

	public FrameResult(List<String> results) {
		this.results = results;
	}

	public FrameResult(List<String> results, Score score) {
		this.results = results;
		this.score = score;
	}

	public Score calculateScore(Score totalScore) {
		if (score == null) {
			return totalScore;
		}
		score = score.sum(totalScore);
		return score;
	}

	public List<String> getRollResults() {
		return results;
	}

	public String getScore() {
		if (score == null) {
			return Score.EMPTY_SCORE_CHARACTER;
		}
		return score.getScoreCharacter();
	}
}
