package domain;

import domain.phase.FinalFramePhaseCollection;
import domain.phase.PhaseCollection;

public class FinalFrame {

	private static final int START_BOWLING_PINS = 10;

	private PhaseCollection phaseCollection;
	private int remainBowlingPins;

	public FinalFrame() {
		this.phaseCollection = new FinalFramePhaseCollection();
		this.remainBowlingPins = START_BOWLING_PINS;
	}

	public PhaseResult shoot(int fallenBowlingPins) {
		validateBowlingPinsCount(fallenBowlingPins);
		remainBowlingPins -= fallenBowlingPins;
		return getPhaseResult();
	}

	private void validateBowlingPinsCount(int fallenBowlingPins) {
		if (remainBowlingPins < fallenBowlingPins) {
			throw new IllegalStateException("서 있는 볼링핀보다 더 많은 볼링핀을 쓰러뜨릴 수는 없겠죠?");
		}
	}

	private PhaseResult getPhaseResult() {
		PhaseResult result = phaseCollection.getFrameResult(remainBowlingPins);
		restoreBallingPinsIfPhaseResultHasNextPhase(result);
		return result;
	}

	private void restoreBallingPinsIfPhaseResultHasNextPhase(PhaseResult result) {
		if (result.hasNextPhase()) {
			remainBowlingPins = START_BOWLING_PINS;
			phaseCollection.addThirdPhase();
		}
	}

}
