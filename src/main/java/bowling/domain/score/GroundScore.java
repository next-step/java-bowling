package bowling.domain.score;

import bowling.domain.DownedPinCount;

public class GroundScore implements Score {

	private static final Score INIT = new GroundScore();

	private GroundScore() {}

	public static Score getInstance() {
		return INIT;
	}

	@Override
	public boolean isRemainExtra() {
		return false;
	}

	@Override
	public void addExtraCount(DownedPinCount count) {}

	@Override
	public DownedPinCount getValue() {
		return DownedPinCount.NO_PIN_DOWN;
	}
}
