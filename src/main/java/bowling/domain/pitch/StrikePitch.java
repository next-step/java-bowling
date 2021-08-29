package bowling.domain.pitch;

import bowling.domain.KnockedPins;

public final class StrikePitch extends BasePitch {

	public StrikePitch() {
		this.knockedPins = KnockedPins.of(STRIKE_PINS_COUNT);
	}

	@Override
	public Pitch play(final KnockedPins knockedPins) {
		if (knockedPins.count() == STRIKE_PINS_COUNT) {
			return new StrikePitch();
		}

		return new NormalPitch(knockedPins);
	}
}
