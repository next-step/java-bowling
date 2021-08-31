package bowling.domain.pitch;

import bowling.domain.common.FalledPins;

public abstract class BasePitch implements Pitch {

	protected static final int STRIKE_PINS_COUNT = 10;

	protected FalledPins falledPins;

	public Pitch play(final int falledPinsCount) {
		return play(FalledPins.of(falledPinsCount));
	}

	public FalledPins getFalledPins() {
		return falledPins;
	}
}
