package bowling.domain.frame;

import java.util.Optional;

import bowling.domain.score.Score;
import bowling.domain.score.Scores;

public interface Frame {
	Frame addNextFrame();

	boolean canPlayMore();

	int getIndex();

	void playFrame(Score score);

	Scores getScores();

	Optional<Score> calculateFrameTotalScore();
}
