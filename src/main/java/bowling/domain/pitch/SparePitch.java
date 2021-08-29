package bowling.domain.pitch;

import bowling.domain.KnockedPins;
import bowling.domain.exception.InvalidPinCountException;

public final class SparePitch extends BasePitch {

	private static final int MIN_KNOCKED_PINS_COUNT = 1;

	public SparePitch(final KnockedPins knockedPins) {
		createValidation(knockedPins);
		this.knockedPins = knockedPins;
	}

	public SparePitch(final int knockedPinsCount) {
		this(KnockedPins.of(knockedPinsCount));
	}

	private void createValidation(final KnockedPins knockedPins) {
		if (knockedPins.count() < MIN_KNOCKED_PINS_COUNT) {
			throw new InvalidPinCountException();
		}
	}

	@Override
	public Pitch play(final KnockedPins knockedPins) {
		if (knockedPins.count() == STRIKE_PINS_COUNT) {
			return new StrikePitch();
		}

		return new NormalPitch(knockedPins);
	}
}
