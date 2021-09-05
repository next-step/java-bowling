package bowling.domain.pitch;

import bowling.domain.common.Pins;
import bowling.domain.exception.InvalidPinCountException;

public final class NormalPitch extends BasePitch {

	private static final int MAX_PINS_COUNT = 9;

	public NormalPitch(final Pins pins) {
		createValidation(pins);
		this.pins = pins;
	}

	public NormalPitch(final int pinsCount) {
		this(Pins.of(pinsCount));
	}

	private void createValidation(final Pins pins) {
		if (pins.count() > MAX_PINS_COUNT) {
			throw new InvalidPinCountException();
		}
	}

	@Override
	public Pitch play(final Pins pins) {
		if (this.pins.count() + pins.count() == STRIKE_PINS_COUNT) {
			return new SparePitch(pins);
		}

		if (this.pins.count() + pins.count() > MAX_PINS_COUNT) {
			throw new InvalidPinCountException();
		}

		return new NormalPitch(pins);
	}
}
