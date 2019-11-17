package domain;

import domain.frame.FinalFrame;
import domain.frame.Frame;
import domain.frame.NormalFrame;
import domain.phase.result.PhaseResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BowlingBoard {

	private static final int NORMAL_PHASE_COUNT = 9;

	// TODO List 2개로 쪼갤 수 있음, 어떤게 더 좋을지는 고민해보자
	// TODO List<PhaseResult> 한 번 더 일급 컬렉션으로 빼는 것 가능
	private Map<Frame, List<PhaseResult>> frames = new LinkedHashMap<>();

	public BowlingBoard() {
		for (int i = 0; i < NORMAL_PHASE_COUNT; i ++) {
			frames.put(new NormalFrame(), new ArrayList<>());
		}
		frames.put(new FinalFrame(), new ArrayList<>());
	}

	int shoot(int currentFrameIndex, int fallenBowlingPins) {
		Frame currentFrame = new ArrayList<>(frames.keySet())
				.get(currentFrameIndex);
		PhaseResult result = currentFrame.shoot(fallenBowlingPins);
		frames.get(currentFrame).add(result);
		return 0;
	}

}
