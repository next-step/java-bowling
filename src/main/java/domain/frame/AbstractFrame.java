package domain.frame;

import domain.FrameNumber;
import domain.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hspark on 04/12/2018.
 */
public abstract class AbstractFrame implements Frame {

	private FrameNumber frameNumber;
	private Frame nextFrame;

	public AbstractFrame(int frameNumber) {
		this.frameNumber = new FrameNumber(frameNumber);
	}

	@Override
	public void setNextFrame(Frame frame) {
		this.nextFrame = frame;
	}

	@Override
	public Frame getNextFrame() {
		return this.nextFrame;
	}

	@Override
	public Frame getLastFrame() {
		if (!isFinished() || (getFrameNumber() == MAX_FRAME && isFinished())) {
			return self();
		}
		return getNextFrame().getLastFrame();
	}

	@Override
	public int getFrameNumber() {
		return frameNumber.toInteger();
	}

	/**
	 * 마지막 프레임이고, 프레임이 끝났으면 다음이 없다.
	 * @return
	 */

	@Override
	public boolean isLeftFrame() {
		domain.frame.Frame lastFrame = getLastFrame();
		return !(lastFrame.isFinished() && lastFrame.getFrameNumber() == MAX_FRAME);
	}

	@Override
	public boolean hasNext() {
		return Objects.nonNull(getNextFrame());
	}

	public FrameResults getFrameResults() {
		List<FrameResult> states = new ArrayList<>();
		return new FrameResults(makeFrameResults(states));
	}

	public List<FrameResult> makeFrameResults(List<FrameResult> frameResults) {
		frameResults.add(new FrameResult(self().getState(), self().getScore()));
		if (hasNext()) {
			getNextFrame().makeFrameResults(frameResults);
		}
		return frameResults;
	}

	@Override
	public Score calculateScore(Score previousScore) {
		Score calculateScore = getState().calculateScore(previousScore);
		if (calculateScore.isScoreCalculateComplete() || Objects.isNull(getNextFrame())) {
			return calculateScore;
		}
		return getNextFrame().calculateScore(calculateScore);
	}
}
