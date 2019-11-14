package domain.frame;

import domain.PhaseResult;
import domain.phase.FinalFramePhaseCollection;

public class FinalFrame extends Frame {

	public FinalFrame() {
		super(new FinalFramePhaseCollection());
	}

	@Override
	public PhaseResult getPhaseResult() {
		PhaseResult result = phaseCollection.getFrameResult(remainBowlingPins);
		restoreBallingPinsIfPhaseResultHasNextPhase(result);
		return result;
	}

	private void restoreBallingPinsIfPhaseResultHasNextPhase(PhaseResult result) {
		if (result.hasNextPhase()) {
			super.restoreBallingPins();
		}
	}

}
