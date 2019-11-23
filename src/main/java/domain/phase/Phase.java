package domain.phase;

import domain.phase.result.PhaseResult;

public interface Phase {

	PhaseResult getFrameResult(int remainBowlingPins);

}
