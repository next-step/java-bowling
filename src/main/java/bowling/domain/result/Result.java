package bowling.domain.result;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public interface Result {

	boolean isMatch(Score first, Score second);

	Score calculateFrameTotalScore(Score currentFrameScore, Scores nextFrameScores);

	String renderString(Scores scores);
}
