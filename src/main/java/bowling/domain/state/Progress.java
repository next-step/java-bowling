package bowling.domain.state;

import java.util.Collections;
import java.util.List;

import bowling.domain.common.Pins;
import bowling.domain.score.Score;

public class Progress extends BasePitchState {

	private final Pins pins;

	private Progress(final Pins pins) {
		this.pins = pins;
	}

	public static Progress of(final Pins pins) {
		return new Progress(pins);
	}

	@Override
	public PitchState hitPins(final Pins pins) {
		final Pins hitPinsTotal = this.pins.add(pins);

		if (hitPinsTotal.isAllHit()) {
			return Spare.of(this.pins);
		}

		return Miss.of(this.pins, pins);
	}

	@Override
	public List<Pins> getHitPins() {
		return Collections.singletonList(pins);
	}

	@Override
	public boolean isProgress() {
		return true;
	}

	@Override
	public Score addBonusScore(final Score score) {
		return score.add(pins.score());
	}
}
