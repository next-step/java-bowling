package domain.phase.result;

public enum NormalPhaseResult implements PhaseResult {

	IN_PROGRESS,
	STRIKE,
	SPARE,
	MISS,
	GUTTER;

	@Override
	public boolean hasNextPhase(PhaseResult prevResult) {
		return this == IN_PROGRESS;
	}

	@Override
	public boolean shouldRestoreBowlingPins() {
		return false;
	}

}
