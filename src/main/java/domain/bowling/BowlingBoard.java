package domain.bowling;

import domain.frame.FinalFrame;
import domain.frame.Frame;
import domain.frame.FrameStore;
import domain.frame.NormalFrame;
import domain.states.BowlingPins;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 볼링 게임 내 결과가 기록되고 있는 볼링판을 의미
 */
public class BowlingBoard {

	private final static int NORMAL_FRAME_COUNT = 9;
	private final static int FINAL_FRAME_INDEX = 10;

	private final List<Frame> frames;
	private int currentFrameIndex;

	private BowlingBoard() {
		List<Frame> frames = new ArrayList<>();
		for (int i = 0; i < NORMAL_FRAME_COUNT; i++) {
			frames.add(NormalFrame.newInstance());
		}
		frames.add(FinalFrame.newInstance());
		this.frames = frames;
		this.currentFrameIndex = 0;
	}

	public static BowlingBoard newInstance() {
		return new BowlingBoard();
	}

	public void roll(BowlingPins pins) {
		FrameStore.plusAdditionalScores(pins);
		Frame currentFrame = frames.get(currentFrameIndex);
		currentFrame.roll(pins);
		enrollFrameAndAddIndexIfEnds(currentFrame);
		plusFinalScoreIfBowlingEnds();
	}

	private void enrollFrameAndAddIndexIfEnds(Frame currentFrame) {
		if (currentFrame.isEnd()) {
			FrameStore.enroll(currentFrame);
			currentFrameIndex++;
		}
	}

	private void plusFinalScoreIfBowlingEnds() {
		if (isEnd()) {
			FrameStore.plusAdditionalScores(BowlingPins.of(0));
		}
	}


	public boolean isEnd() {
		return currentFrameIndex == FINAL_FRAME_INDEX;
	}

	public List<Optional<Integer>> getScores() {
		return frames.stream()
				.map(Frame::getOptionalScore)
				.collect(Collectors.toList());
	}

}
