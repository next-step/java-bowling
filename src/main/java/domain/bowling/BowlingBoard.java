package domain.bowling;

import domain.frame.FinalFrame;
import domain.frame.Frame;
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
		addScoreOnPreviousFrames(pins);
		Frame currentFrame = getCurrentFrame();
		currentFrame.roll(pins);
		if (currentFrame.isEnd()) {
			addPreviousScoreIfExist();
			currentFrameIndex++;
		}
	}

	private void addScoreOnPreviousFrames(BowlingPins pins) {
		for (int i = 0; i < currentFrameIndex; i++) {
			frames.get(i).addNextFrameScore(pins);
		}
	}

	private Frame getCurrentFrame() {
		return frames.get(currentFrameIndex);
	}

	private void addPreviousScoreIfExist() {
		if (currentFrameIndex > 0) {
			getCurrentFrame().addPreviousScore(getPrevFrame().getScore());
		}
	}

	private Frame getPrevFrame() {
		return frames.get(currentFrameIndex - 1);
	}

	public boolean isEnd() {
		return currentFrameIndex == 10;
	}

	public List<Optional<Integer>> getScores() {
		return frames.stream()
				.map(Frame::getOptionalScore)
				.collect(Collectors.toList());
	}

}
