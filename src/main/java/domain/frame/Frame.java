package domain.frame;

import domain.states.BowlingPins;

import java.util.Optional;

public interface Frame {

	void roll(BowlingPins pins);

	void addNextFrameScore(BowlingPins pins);

	void addPreviousScore(int prevScore);

	Optional<Integer> getOptionalScore();

	boolean isScoreCalculationEnd();

	boolean isEnd();

}
