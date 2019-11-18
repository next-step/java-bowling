package domain.phase.result;

public enum FinalPhaseResult implements PhaseResult {

	IN_PROGRESS,
	STRIKE,
	SPARE,
	MISS,
	GUTTER,
	LAST_SCORE; // 마지막 프레임의 세 번째 페이스의 점수


	@Override
	public boolean hasNextPhase(PhaseResult prevResult) {
		if (prevResult == STRIKE) {
			return true;
		}

		return this == STRIKE || this == SPARE || this == IN_PROGRESS;
	}

	@Override
	public boolean shouldRestoreBowlingPins() {
		return this == STRIKE || this == SPARE;
	}

}
