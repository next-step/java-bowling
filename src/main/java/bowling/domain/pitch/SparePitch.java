package bowling.domain.pitch;

import bowling.domain.common.Pins;
import bowling.domain.exception.InvalidPinCountException;

public final class SparePitch extends BasePitch {

	private static final int MIN_PINS_COUNT = 1;

	public SparePitch(final Pins pins) {
		createValidation(pins);
		this.pins = pins;
	}

	private void createValidation(final Pins pins) {
		if (pins.count() < MIN_PINS_COUNT) {
			throw new InvalidPinCountException();
		}
	}

	@Override
	public Pitch play(final Pins pins) {
		if (pins.count() == STRIKE_PINS_COUNT) {
			return new StrikePitch();
		}

		return new NormalPitch(pins);
	}
}
