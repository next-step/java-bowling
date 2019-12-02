package domain.bowling;

import domain.BowlingPins;
import domain.frame.Frame;
import domain.frame.FrameStore;
import domain.frame.Frames;
import domain.states.States;

import java.util.List;
import java.util.Optional;

/**
 * 볼링 게임 내 결과가 기록되고 있는 볼링판을 의미
 */
public class BowlingBoard {

	private final Frames frames;
	private final FrameStore frameStore;

	private BowlingBoard() {
		frames = Frames.newInstance();
		frameStore = new FrameStore();
	}

	public static BowlingBoard newInstance() {
		return new BowlingBoard();
	}

	public boolean roll(BowlingPins pins) {
		frameStore.plusAdditionalScores(pins);
		Frame currentFrame = frames.roll(pins);
		enrollFrameAndAddIndexIfEnds(currentFrame);
		plusFinalScoreIfBowlingEnds();
		return currentFrame.isEnd();
	}

	private void enrollFrameAndAddIndexIfEnds(Frame currentFrame) {
		if (currentFrame.isEnd()) {
			frameStore.enroll(currentFrame);
			frames.moveToNextFrame();
		}
	}

	private void plusFinalScoreIfBowlingEnds() {
		if (isEnd()) {
			frameStore.plusAdditionalScores(BowlingPins.of(0));
		}
	}

	boolean isEnd() {
		return frames.isEnd();
	}

	int getCurrentFrameIndex() {
		return frames.getCurrentFrameIndex();
	}

	public List<States> getStates() {
		return frames.getStates();
	}

	public List<Optional<Integer>> getScores() {
		return frames.getScores();
	}

}
