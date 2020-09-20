package bowling.domain.score;

import bowling.domain.DownedPinCount;

public class PlayingScore implements Score {

	public PlayingScore(DownedPinCount first, Score accumulated) {
		
	}

	@Override
	public boolean isRemainExtra() {
		return false;
	}

	@Override
	public void addExtraCount(DownedPinCount count) {

	}

	@Override
	public DownedPinCount getValue() {
		return DownedPinCount.NO_PIN_DOWN;
	}
}
