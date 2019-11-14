package domain.phase;

import domain.PhaseResult;

import java.util.List;

public abstract class PhaseCollection {

	private static final int START_INDEX = 0;

	final List<Phase> phaseList;
	int currentPhaseIndex;

	PhaseCollection(List<Phase> phaseList) {
		this.phaseList = phaseList;
		this.currentPhaseIndex = START_INDEX;
	}

	public PhaseResult getFrameResult(int remainBowlingPins) {
		validateCurrentPhaseIndex();
		return phaseList.get(currentPhaseIndex++).getFrameResult(remainBowlingPins);
	}

	abstract void validateCurrentPhaseIndex();

	public abstract void addThirdPhase();

}
