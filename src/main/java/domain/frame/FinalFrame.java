package domain.frame;

import domain.phase.FinalFramePhaseCollection;
import domain.phase.result.PhaseResult;

public class FinalFrame extends Frame {

	public FinalFrame() {
		super(new FinalFramePhaseCollection());
	}

	@Override
	public PhaseResult getPhaseResult() {
		PhaseResult result = phaseCollection.getFrameResult(remainBowlingPins);
		restoreBowlingPinsIfPhaseResultHasNextPhase(result);
		return result;
	}

	private void restoreBowlingPinsIfPhaseResultHasNextPhase(PhaseResult result) {
		if (result.shouldRestoreBowlingPins()) {
			super.restoreBallingPins();
		}
	}

}
