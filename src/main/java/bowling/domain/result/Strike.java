package bowling.domain.result;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public class Strike implements Result {

	@Override
	public boolean isMatch(Score first, Score second) {
		return first.isScoreTen() && second.isScoreZero();
	}

	@Override
	public Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores) {
		return currentFrameScore.add(nextFrameScores.calculateFrameTotalScore());
	}

	@Override
	public String renderString(Scores scores) {
		return "   X   ";
	}
}
