package domain.bowlling;

import domain.frame.FinalFrame;
import domain.frame.Frame;
import domain.frame.NormalFrame;
import domain.frame.result.FrameResult;
import domain.phase.result.PhaseResult;

import java.util.*;

public class BowlingBoard {

	private static final int NORMAL_PHASE_COUNT = 9;

	private List<Frame> frames = new ArrayList<>();
	private List<FrameResult> frameResults = new ArrayList<>();

	public BowlingBoard() {
		for (int i = 0; i < NORMAL_PHASE_COUNT; i ++) {
			frames.add(new NormalFrame());
			frameResults.add(new FrameResult());
		}
		frames.add(new FinalFrame());
		frameResults.add(new FrameResult());
	}

	public int shoot(int currentFrameIndex, int fallenBowlingPins) {
		Frame currentFrame = frames.get(currentFrameIndex);
		PhaseResult result = currentFrame.shoot(fallenBowlingPins);
		PhaseResult prevResult = getPrevPhaseResult(currentFrameIndex);

		frameResults.get(currentFrameIndex).saveResult(result, fallenBowlingPins);
		return result.hasNextPhase(prevResult) ? currentFrameIndex : currentFrameIndex + 1;
	}

	private PhaseResult getPrevPhaseResult(int currentFrameIndex) {
		return frameResults.get(currentFrameIndex).getPrevResult();
	}

	public List<FrameResult> getPhaseResults() {
		return new ArrayList<>(frameResults);
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
