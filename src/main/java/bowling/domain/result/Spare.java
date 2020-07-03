package bowling.domain.result;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Spare implements Result {

	@Override
	public boolean isMatch(Score first, Score second) {
		return ! first.isScoreTen() && first.add(second).isScoreTen();
	}

	@Override
	public Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores) {
		return nextFrameScores.getFirst()
			.map(score -> score.add(currentFrameScore))
			.orElse(currentFrameScore);
	}

	@Override
	public String renderString(Scores scores) {
		return String.format("  %d|/   ",
			scores.getFirst().orElseThrow(() -> new IllegalArgumentException("not found")).getScore());
	}
}
