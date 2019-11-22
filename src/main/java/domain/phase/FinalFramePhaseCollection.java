package domain.phase;

import java.util.ArrayList;
import java.util.Arrays;

public class FinalFramePhaseCollection extends PhaseCollection {

	private static final int MAX_INDEX = 2;

	public FinalFramePhaseCollection() {
		super(new ArrayList<>(Arrays.asList(FinalPhase.FIRST_PHASE, FinalPhase.SECOND_PHASE)));
	}

	@Override
	void validateCurrentPhaseIndex() {
		if (currentPhaseIndex > MAX_INDEX) {
			throw new IllegalStateException("페이즈가 모두 종료되었습니다");
		}
	}

	@Override
	public void addThirdPhase() {
		phases.add(FinalPhase.THIRD_PHASE);
	}

}
