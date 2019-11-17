package domain.phase.result;

public enum FinalPhaseResult implements PhaseResult {

	UNKNOWN,
	IN_PROGRESS,
	STRIKE,
	SPARE,
	MISS,
	GUTTER,
	LAST_SCORE; // 마지막 프레임의 세 번째 페이스의 점수


	// TODO: 2019-11-17 요구사항 체크, finalFrame 의 첫번째 Phase 가 STRIKE 이고
	//  두 번째 PHASE 의 결과가 IN_PROGRESS 이면 세 번째 존재 ?
	@Override
	public boolean hasNextPhase() {
		return this == STRIKE || this == SPARE;
	}

}
