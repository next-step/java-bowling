package bowling.domain.result;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Miss implements Result {

	@Override
	public boolean isMatch(Score first, Score second) {
		return !first.isScoreTen() && first.add(second).isBetweenInRange();
	}

	@Override
	public Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores) {
		return currentFrameScore;
	}

	@Override
	public String renderString(Scores scores) {
		return String.format("   %d|%d   ",
			scores.getFirst().orElseThrow(() -> new IllegalArgumentException("not found")).getScore(),
			scores.getSecond().orElseThrow(() -> new IllegalArgumentException("not found")).getScore());
	}
}
