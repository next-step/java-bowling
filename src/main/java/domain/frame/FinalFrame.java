package domain.frame;

import domain.score.Score;
import domain.states.BowlingPins;

import java.util.Optional;

public class FinalFrame implements Frame {

	@Override
	public void roll(BowlingPins pins) {

	}

	@Override
	public void initializeScore(Score score) {

	}

	@Override
	public Optional<Integer> getScore() {
		return Optional.empty();
	}

	@Override
	public void addScore(BowlingPins pins) {

	}

}
