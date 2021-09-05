package bowling.domain.pitch;

import bowling.domain.common.Pins;
import bowling.domain.exception.InvalidPinCountException;

public final class SparePitch extends BasePitch {

	private static final Pins MIN_PINS = Pins.of(1);

	public SparePitch(final Pins pins) {
		createValidation(pins);
		this.pins = pins;
	}

	private void createValidation(final Pins pins) {
		if (pins.isSmallerThan(MIN_PINS)) {
			throw new InvalidPinCountException();
		}
	}

	@Override
	public Pitch play(final Pins pins) {
		if (pins.isEqualsCount(STRIKE_PINS_COUNT)) {
			return new StrikePitch();
		}

		return new NormalPitch(pins);
	}
}
