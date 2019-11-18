package domain.phase.result;

public enum FinalPhaseResult implements PhaseResult {

	IN_PROGRESS("%s"),
	STRIKE("X"),
	SPARE("/"),
	MISS("%s"),
	GUTTER("-"),
	LAST_SCORE("%s"); // 마지막 프레임의 세 번째 페이스의 점수

	private final String format;

	FinalPhaseResult(String format) {
		this.format = format;
	}

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

	@Override
	public String toSign(int fallenBowlingPins) {
		return String.format(format, fallenBowlingPins);
	}

}
