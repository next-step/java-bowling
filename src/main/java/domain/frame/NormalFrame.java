package domain.frame;

import domain.PhaseResult;
import domain.phase.NormalFramePhaseCollection;

public class NormalFrame extends Frame {

	public NormalFrame() {
		super(new NormalFramePhaseCollection());
	}

	@Override
	public PhaseResult getPhaseResult() {
		return phaseCollection.getFrameResult(remainBowlingPins);
	}

}
