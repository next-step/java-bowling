package domain.phase.result;

public enum NormalPhaseResult implements PhaseResult {

	UNKNOWN,
	IN_PROGRESS,
	STRIKE,
	SPARE,
	MISS,
	GUTTER;

	@Override
	public boolean hasNextPhase() {
		return this == IN_PROGRESS;
	}

}
