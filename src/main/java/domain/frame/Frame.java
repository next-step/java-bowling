package domain.frame;

import domain.score.Score;
import domain.states.BowlingPins;

import java.util.Optional;

public interface Frame {

	void roll(BowlingPins pins);

	void initializeScore(Score score);

	Optional<Integer> getScore();

	void addScore(BowlingPins pins);

}
