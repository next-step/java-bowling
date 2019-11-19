package domain.frame;

import domain.phase.PhaseCollection;
import domain.phase.result.PhaseResult;

import java.util.Objects;

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
		if (fallenBowlingPins < 0) {
			throw  new IllegalArgumentException(String.format("어떻게 음수(%s)의 볼링핀을 쓰러뜨리나요 ㄷ...", fallenBowlingPins));
		}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Frame frame = (Frame) o;
		return remainBowlingPins == frame.remainBowlingPins &&
				Objects.equals(phaseCollection, frame.phaseCollection);
	}

	@Override
	public int hashCode() {
		return Objects.hash(phaseCollection, remainBowlingPins);
	}

}
