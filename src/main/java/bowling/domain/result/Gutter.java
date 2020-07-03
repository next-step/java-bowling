package bowling.domain.result;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Gutter implements Result {

	@Override
	public boolean isMatch(Score first, Score second) {
		return first.isScoreZero() && first.add(second).isScoreZero();
	}

	@Override
	public Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores) {
		return currentFrameScore;
	}

	@Override
	public String renderString(Scores scores) {
		return "   -   ";
	}
}
