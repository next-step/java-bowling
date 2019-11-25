package domain.frame;

import domain.BowlingPins;
import domain.states.States;

import java.util.Optional;

public interface Frame {

	void roll(BowlingPins pins);

	void addNextFrameScore(BowlingPins pins);

	void addPreviousScore(int prevScore);

	Optional<Integer> getOptionalScore();

	boolean isScoreCalculationEnd();

	boolean isEnd();

	States getStates();

}
