package domain;

public enum PhaseResult {

	UNKNOWN(false),
	IN_PROGRESS(false),
	STRIKE(true),
	SPARE(true),
	MISS(false),
	GUTTER(false),
	LAST_SCORE(false); // 마지막 프레임의 세 번째 페이스의 점수

	private final boolean hsaNextPhaseInFinalFrame;

	PhaseResult(boolean hsaNextPhaseInFinalFrame) {
		this.hsaNextPhaseInFinalFrame = hsaNextPhaseInFinalFrame;
	}

	public boolean hasNextPhase() {
		return hsaNextPhaseInFinalFrame;
	}

}
