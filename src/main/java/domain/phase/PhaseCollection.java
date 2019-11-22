package domain.phase;


import domain.phase.result.PhaseResult;

import java.util.List;
import java.util.Objects;

public abstract class PhaseCollection {

	private static final int START_INDEX = 0;

	final List<Phase> phases;
	int currentPhaseIndex;

	PhaseCollection(List<Phase> phases) {
		this.phases = phases;
		this.currentPhaseIndex = START_INDEX;
	}

	public PhaseResult getFrameResult(int remainBowlingPins) {
		validateCurrentPhaseIndex();
		return phases.get(currentPhaseIndex++).getFrameResult(remainBowlingPins);
	}

	abstract void validateCurrentPhaseIndex();

	public abstract void addThirdPhase();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PhaseCollection that = (PhaseCollection) o;
		return currentPhaseIndex == that.currentPhaseIndex &&
				Objects.equals(phases, that.phases);
	}

	@Override
	public int hashCode() {
		return Objects.hash(phases, currentPhaseIndex);
	}

}
