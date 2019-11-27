package domain.frame;

import domain.BowlingPins;

import java.util.*;

/**
 * 점수 계산을 위한 Frame을 관리할 존재
 */
public class FrameStore {

	/**
	 * 등록된 Frame 의 score 는 left 가 1이상 존재하는 경우
	 * score 더하는 기능이 있어야 하고 left 가 0이 되면, 다음 score 에 점수를 더한다
	 */
	private final List<Frame> frames = new ArrayList<>();
	private final List<Boolean> isAddedToNextFrame = new ArrayList<>();

	public void enroll(Frame frame) {
		frames.add(frame);
		isAddedToNextFrame.add(false);
	}

	public void plusAdditionalScores(BowlingPins pins) {
		for (Frame frame : frames) {
			frame.addNextFrameScore(pins);
		}
		plusScoreToNextFrameIfCalculationEnd();
	}

	private void plusScoreToNextFrameIfCalculationEnd() {
		for (int i = 0; i < frames.size(); i++) {
			plusScoreToNextFrameIfCalculationEnd(i);
		}
	}

	private void plusScoreToNextFrameIfCalculationEnd(int index) {
		if (isFrameCalculationEnd(index) && hasNextFrame(index)) {
			frames.get(index + 1).addPreviousScore(frames.get(index).getOptionalScore().orElse(0));
			isAddedToNextFrame.set(index, true);
		}
	}

	private boolean isFrameCalculationEnd(int index) {
		return frames.get(index).isScoreCalculationEnd() && !isAddedToNextFrame.get(index);
	}

	private boolean hasNextFrame(int index) {
		return index + 1 < frames.size();
	}

}
