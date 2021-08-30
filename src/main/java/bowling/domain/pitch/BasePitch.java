package bowling.domain.pitch;

import bowling.domain.common.KnockedPins;

public abstract class BasePitch implements Pitch {

	protected static final int STRIKE_PINS_COUNT = 10;

	protected KnockedPins knockedPins;

	public Pitch play(final int knockedPinsCount) {
		return play(KnockedPins.of(knockedPinsCount));
	}

	public KnockedPins getKnockedPins() {
		return knockedPins;
	}
}
