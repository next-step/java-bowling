package domain.frame;

import domain.score.Score;
import domain.states.BowlingPins;

import java.util.Optional;

public interface Frame {

	void roll(BowlingPins pins);

	Optional<Integer> getOptionalScore();

	Score getScore();

	void addNextFrameScore(BowlingPins pins);

	void addPreviousScore(Score prevScore);

	boolean isEnd();

}
