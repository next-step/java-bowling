package bowling.domain.pitch;

import bowling.domain.common.FalledPins;
import bowling.domain.exception.InvalidPinCountException;

public final class SparePitch extends BasePitch {

	private static final int MIN_FALLED_PINS_COUNT = 1;

	public SparePitch(final FalledPins falledPins) {
		createValidation(falledPins);
		this.falledPins = falledPins;
	}

	private void createValidation(final FalledPins falledPins) {
		if (falledPins.count() < MIN_FALLED_PINS_COUNT) {
			throw new InvalidPinCountException();
		}
	}

	@Override
	public Pitch play(final FalledPins falledPins) {
		if (falledPins.count() == STRIKE_PINS_COUNT) {
			return new StrikePitch();
		}

		return new NormalPitch(falledPins);
	}
}
