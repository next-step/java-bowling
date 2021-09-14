package bowling.domain.state;

import java.util.Collections;
import java.util.List;

import bowling.domain.common.Pins;

public class Start extends BasePitchState {

	private Start() {
	}

	public static Start of() {
		return new Start();
	}

	@Override
	public PitchState hitPins(final Pins pins) {
		if (pins.isAllHit()) {
			return Strike.of();
		}

		return Progress.of(pins);
	}

	@Override
	public boolean isFinish() {
		return false;
	}

	@Override
	public boolean isProgress() {
		return true;
	}

	@Override
	public List<Pins> getHitPins() {
		return Collections.emptyList();
	}
}
