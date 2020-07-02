package bowling.domain.frame;

import java.util.Optional;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public interface Frame {
	Frame addNextFrame();

	boolean canPlayMore();

	int getIndex();

	void addScore(Score score);

	void addFirstScore(Score firstScore);

	void addSecondScore(Score secondScore);

	void addBonusScore(Score bonusScore);

	Scores getScores();

	Optional<Score> calculateFrameTotalScore();
}
