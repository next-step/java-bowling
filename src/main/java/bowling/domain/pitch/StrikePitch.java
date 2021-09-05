package bowling.domain.pitch;

import bowling.domain.common.Pins;

public final class StrikePitch extends BasePitch {

	public StrikePitch() {
		this.pins = Pins.of(STRIKE_PINS_COUNT);
	}

	@Override
	public Pitch play(final Pins pins) {
		if (pins.count() == STRIKE_PINS_COUNT) {
			return new StrikePitch();
		}

		return new NormalPitch(pins);
	}
}
