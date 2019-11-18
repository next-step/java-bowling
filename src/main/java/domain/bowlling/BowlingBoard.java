package domain.bowlling;

import domain.frame.FinalFrame;
import domain.frame.Frame;
import domain.frame.NormalFrame;
import domain.frame.result.FrameResult;
import domain.phase.result.PhaseResult;

import java.util.*;

/**
 * 한 사람에게 할당되는 볼링 점수판 한 줄로 투구 결과가 기록된다
 */
public class BowlingBoard {

	@SuppressWarnings("FieldCanBeLocal")
	private static final int START_FRAME_INDEX = 0;
	@SuppressWarnings("FieldCanBeLocal")
	private static final int FINAL_FRAME_INDEX = 9;
	private static final int NORMAL_PHASE_COUNT = 9;

	private int currentFrameIndex;
	// TODO: 2019-11-18 두 개를 합쳐야함
	private List<FrameResult> frameResults = new ArrayList<>();
	private List<Frame> frames = new ArrayList<>();

	public BowlingBoard() {
		this(START_FRAME_INDEX);
	}

	public BowlingBoard(int currentFrameIndex) {
		this.currentFrameIndex = currentFrameIndex;
		for (int i = 0; i < NORMAL_PHASE_COUNT; i ++) {
			this.frames.add(new NormalFrame());
			this.frameResults.add(new FrameResult());
		}
		this.frames.add(new FinalFrame());
		this.frameResults.add(new FrameResult());
	}

	public void shoot(int fallenBowlingPins) {
		validateCurrentFrame();
		Frame currentFrame = frames.get(currentFrameIndex);
		PhaseResult result = currentFrame.shoot(fallenBowlingPins);
		PhaseResult prevResult = getPrevPhaseResult(currentFrameIndex);

		frameResults.get(currentFrameIndex).saveResult(result, fallenBowlingPins);
		if (!result.hasNextPhase(prevResult)) {
			currentFrameIndex++;
		}
	}

	private void validateCurrentFrame() {
		if (this.currentFrameIndex > FINAL_FRAME_INDEX) {
			throw new IllegalStateException("종료된 볼링 게임입니다");
		}
	}

	private PhaseResult getPrevPhaseResult(int currentFrameIndex) {
		return frameResults.get(currentFrameIndex).getPrevResult();
	}

	public List<FrameResult> getPhaseResults() {
		return new ArrayList<>(frameResults);
	}

	public int getCurrentFrameIndex() {
		return currentFrameIndex;
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
