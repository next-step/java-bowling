package domain.frame;

import domain.PhaseResult;
import domain.phase.PhaseCollection;

public abstract class Frame {

	private static final int START_BOWLING_PINS = 10;

	final PhaseCollection phaseCollection;
	int remainBowlingPins;

	Frame(PhaseCollection phaseCollection) {
		this.phaseCollection = phaseCollection;
		this.remainBowlingPins = START_BOWLING_PINS;
	}

	public PhaseResult shoot(int fallenBowlingPins) {
		validateBowlingPinsCount(fallenBowlingPins);
		remainBowlingPins -= fallenBowlingPins;
		return getPhaseResult();
	}

	private void validateBowlingPinsCount(int fallenBowlingPins) {
		if (remainBowlingPins < fallenBowlingPins) {
			throw new IllegalArgumentException(String.format(
					"서 있는 볼링핀(%s)보다 더 많은 볼링핀(%s)을 쓰러뜨릴 수는 없겠죠?", remainBowlingPins, fallenBowlingPins));
		}
	}

	public abstract PhaseResult getPhaseResult();

	void restoreBallingPins() {
		remainBowlingPins = START_BOWLING_PINS;
		phaseCollection.addThirdPhase();
	}

}
