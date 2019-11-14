package domain.phase;

import domain.PhaseResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalFramePhaseCollection implements PhaseCollection {

	private static final int START_INDEX = 0;
	private static final int MAX_INDEX = 2;

	private final List<Phase> phaseList;
	private int currentPhaseIndex;

	public FinalFramePhaseCollection() {
		this.phaseList = new ArrayList<>(Arrays.asList(Phase.FIRST_PHASE, Phase.SECOND_PHASE));
		this.currentPhaseIndex = START_INDEX;
	}

	@Override
	public PhaseResult getFrameResult(int remainBowlingPins) {
		if (currentPhaseIndex > MAX_INDEX) {
			throw new IllegalStateException("페이즈가 모두 종료되었습니다");
		}

		return phaseList.get(currentPhaseIndex++).getFrameResult(remainBowlingPins);
	}

	@Override
	public void addThirdPhase() {
		phaseList.add(Phase.THIRD_PHASE);
	}

}
