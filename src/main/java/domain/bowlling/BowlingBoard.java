package domain.bowlling;

import domain.frame.FinalFrame;
import domain.frame.Frame;
import domain.frame.NormalFrame;
import domain.phase.result.PhaseResult;

import java.util.*;

public class BowlingBoard {

	private static final int NORMAL_PHASE_COUNT = 9;

	// TODO 각각을 일급 컬렉션으로 뺄 수 있음
	// TODO List<PhaseResult> 한 번 더 일급 컬렉션으로 빼는 것 가능, 이름은 FrameResult 로
	// TODO: 2019-11-17 결과와 함께 몇 개를 쓰러뜨렸는지 어딘가에서 저장하고 있어야 하는데? 그래야 출력해줌
	private List<Frame> frames = new ArrayList<>();
	private List<List<PhaseResult>> frameResults = new ArrayList<>();

	public BowlingBoard() {
		for (int i = 0; i < NORMAL_PHASE_COUNT; i ++) {
			frames.add(new NormalFrame());
			frameResults.add(new ArrayList<>());
		}
		frames.add(new FinalFrame());
		frameResults.add(new ArrayList<>());
	}

	public int shoot(int currentFrameIndex, int fallenBowlingPins) {
		Frame currentFrame = frames.get(currentFrameIndex);
		PhaseResult result = currentFrame.shoot(fallenBowlingPins);
		PhaseResult prevResult = getPrevPhaseResult(currentFrameIndex);

		frameResults.get(currentFrameIndex).add(result);
		return result.hasNextPhase(prevResult) ? currentFrameIndex : currentFrameIndex + 1;
	}

	private PhaseResult getPrevPhaseResult(int currentFrameIndex) {
		return frameResults.get(currentFrameIndex).stream()
				.min(Collections.reverseOrder())
				.orElse(null);
	}

	public List<List<PhaseResult>> getPhaseResults() {
		return frameResults;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BowlingBoard that = (BowlingBoard) o;
		return Objects.equals(frames, that.frames) &&
				Objects.equals(frameResults, that.frameResults);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frames, frameResults);
	}

}
