package bowling.domain.pitch;

import bowling.domain.common.FalledPins;
import bowling.domain.exception.InvalidPinCountException;

public final class NormalPitch extends BasePitch {

	private static final int MAX_FALLED_PINS_COUNT = 9;

	public NormalPitch(final FalledPins falledPins) {
		createValidation(falledPins);
		this.falledPins = falledPins;
	}

	public NormalPitch(final int falledPinsCount) {
		this(FalledPins.of(falledPinsCount));
	}

	private void createValidation(final FalledPins falledPins) {
		if (falledPins.count() > MAX_FALLED_PINS_COUNT) {
			throw new InvalidPinCountException();
		}
	}

	@Override
	public Pitch play(final FalledPins falledPins) {
		if (this.falledPins.count() + falledPins.count() == STRIKE_PINS_COUNT) {
			return new SparePitch(falledPins);
		}

		if (this.falledPins.count() + falledPins.count() > MAX_FALLED_PINS_COUNT) {
			throw new InvalidPinCountException();
		}

		return new NormalPitch(falledPins);
	}
}
