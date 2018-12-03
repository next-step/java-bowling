package domain.frame;

import domain.FrameNumber;
import domain.FrameScores;
import domain.Pin;
import domain.Score;
import domain.frame.result.FrameResult;
import domain.frame.result.FrameResults;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by hspark on 21/11/2018.
 */
public abstract class Frame {
	public static final int MAX_FRAME = 10;

	private FrameNumber frameNumber;
	private Frame nextFrame;

	public Frame(int frameNumber) {
		this.frameNumber = new FrameNumber(frameNumber);
	}

	public static Frame first() {
		return new NormalFrame(1);
	}

	abstract public Frame pitch(Pin pin);

	abstract public FrameResult getFrameResult();

	abstract public boolean isFinished();

	abstract Frame self();

	public int getFrameNumber() {
		return frameNumber.toInteger();
	}

	protected void setNextFrame(Frame frame) {
		this.nextFrame = frame;
	}

	protected Frame getNextFrame() {
		return this.nextFrame;
	}

	public Frame getLastFrame() {
		if (!isFinished() || (getFrameNumber() == MAX_FRAME && isFinished())) {
			return self();
		}
		return getNextFrame().getLastFrame();
	}

	/**
	 * 마지막 프레임이고, 프레임이 끝났으면 다음이 없다.
	 * @return
	 */
	public boolean isLeftFrame() {
		Frame lastFrame = getLastFrame();
		return !(lastFrame.isFinished() && lastFrame.getFrameNumber() == MAX_FRAME);
	}

	public boolean hasNext() {
		return Objects.nonNull(getNextFrame());
	}

	public FrameResults getFrameResults() {
		List<FrameResult> frameResults = new ArrayList<>();
		return new FrameResults(makeFrameResults(frameResults));
	}

	protected List<FrameResult> makeFrameResults(List<FrameResult> frameResults) {
		frameResults.add(self().getFrameResult());
		if (hasNext()) {
			nextFrame.makeFrameResults(frameResults);
		}
		return frameResults;
	}

	//점수 계산이 가능할 때 까지 모든 점수를 구한다.
	public FrameScores getScores() {
		List<Integer> scores = new ArrayList<>();
		return new FrameScores(makeScoreResult(scores));
	}

	protected List<Integer> makeScoreResult(List<Integer> scores) {
		scores.add(self().getScore());
		if (hasNext() && nextFrame.isFinished()) {
			nextFrame.makeScoreResult(scores);
		}
		return scores;
	}

	public abstract int getScore();

	public Score calculateScore(Score previousScore) {
		Score calculateScore = getFrameResult().calculateScore(previousScore);
		if (calculateScore.isScoreCalculateComplete() || Objects.isNull(nextFrame)) {
			return calculateScore;
		}
		return getNextFrame().calculateScore(calculateScore);
	}
}
