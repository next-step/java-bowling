package domain.frame;

import domain.phase.NormalFramePhaseCollection;
import domain.phase.result.PhaseResult;

public class NormalFrame extends Frame {

	public NormalFrame() {
		super(new NormalFramePhaseCollection());
	}

	@Override
	public PhaseResult getPhaseResult() {
		return phaseCollection.getFrameResult(remainBowlingPins);
	}

}
