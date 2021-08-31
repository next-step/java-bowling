package bowling.domain.pitch;

import bowling.domain.common.FalledPins;

public final class StrikePitch extends BasePitch {

	public StrikePitch() {
		this.falledPins = FalledPins.of(STRIKE_PINS_COUNT);
	}

	@Override
	public Pitch play(final FalledPins falledPins) {
		if (falledPins.count() == STRIKE_PINS_COUNT) {
			return new StrikePitch();
		}

		return new NormalPitch(falledPins);
	}
}
