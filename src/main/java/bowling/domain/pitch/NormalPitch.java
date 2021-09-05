package bowling.domain.pitch;

import bowling.domain.common.Pins;
import bowling.domain.exception.InvalidPinCountException;

public final class NormalPitch extends BasePitch {

	private static final Pins MAX_PINS = Pins.of(9);

	public NormalPitch(final Pins pins) {
		createValidation(pins);
		this.pins = pins;
	}

	public NormalPitch(final int pinsCount) {
		this(Pins.of(pinsCount));
	}

	private void createValidation(final Pins pins) {
		if (pins.isBiggerThan(MAX_PINS)) {
			throw new InvalidPinCountException();
		}
	}

	@Override
	public Pitch play(final Pins pins) {
		if (this.pins.sumCount(pins) == STRIKE_PINS_COUNT) {
			return new SparePitch(pins);
		}

		if (MAX_PINS.isSmallerThan(this.pins.sumCount(pins))) {
			throw new InvalidPinCountException();
		}

		return new NormalPitch(pins);
	}
}
