package bowling.domain.pitch;

import bowling.domain.KnockedPins;
import bowling.domain.exception.InvalidPinCountException;

public final class NormalPitch extends BasePitch {

	private static final int MAX_KNOCKED_PINS_COUNT = 9;

	public NormalPitch(final KnockedPins knockedPins) {
		createValidation(knockedPins);
		this.knockedPins = knockedPins;
	}

	public NormalPitch(final int knockedPinsCount) {
		this(KnockedPins.of(knockedPinsCount));
	}

	private void createValidation(final KnockedPins knockedPins) {
		if (knockedPins.count() > MAX_KNOCKED_PINS_COUNT) {
			throw new InvalidPinCountException();
		}
	}

	@Override
	public Pitch play(final KnockedPins knockedPins) {
		if (this.knockedPins.count() + knockedPins.count() == STRIKE_PINS_COUNT) {
			return new SparePitch(knockedPins);
		}

		if (this.knockedPins.count() + knockedPins.count() > MAX_KNOCKED_PINS_COUNT) {
			throw new InvalidPinCountException();
		}

		return new NormalPitch(knockedPins);
	}
}
