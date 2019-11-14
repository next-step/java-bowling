package domain.phase;

import domain.PhaseResult;

public interface PhaseCollection {

	PhaseResult getFrameResult(int remainBowlingPins);

	void addThirdPhase();

}
