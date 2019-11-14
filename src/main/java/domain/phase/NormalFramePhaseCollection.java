package domain.phase;

import java.util.Arrays;

public class NormalFramePhaseCollection extends PhaseCollection {

	private static final int MAX_INDEX = 1;

	public NormalFramePhaseCollection() {
		super(Arrays.asList(Phase.FIRST_PHASE, Phase.SECOND_PHASE));
	}

	@Override
	void validateCurrentPhaseIndex() {
		if (currentPhaseIndex > MAX_INDEX) {
			throw new IllegalStateException("두 번째 페이즈까지 모두 종료되었습니다");
		}
	}

	@Override
	public void addThirdPhase() {
		throw new UnsupportedOperationException("1 ~ 9번 프레임에서는 세 번째 페이즈가 없습니다");
	}

}
