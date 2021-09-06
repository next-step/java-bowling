package bowling.domain.pitch;

import bowling.domain.common.Pins;

public abstract class BasePitch implements Pitch {

	protected static final int STRIKE_PINS_COUNT = 10;

	protected Pins pins;

	public Pitch play(final int pinsCount) {
		return play(Pins.of(pinsCount));
	}

	public Pins getPins() {
		return pins;
	}
}
