package domain.frame;

import domain.FrameScores;
import domain.Pin;
import domain.Score;
import domain.frame.result.FrameResult;
import domain.frame.result.None;

/**
 * Created by hspark on 30/11/2018.
 */
public class NormalFrame extends Frame {
	private FrameResult frameResult;

	public NormalFrame(int frameNumber) {
		super(frameNumber);
		this.frameResult = new None(frameNumber);
	}

	@Override
	public Frame pitch(Pin pin) {
		if (frameResult.isFinished()) {
			return getNextFrame().pitch(pin);
		}
		this.frameResult = frameResult.tryBowl(pin);
		return next();
	}

	public Frame next() {
		if (frameResult.isFinished() && !isLastNormalFrame()) {
			setNextFrame(new NormalFrame(getFrameNumber() + 1));
			return getNextFrame();
		}
		if (frameResult.isFinished() && isLastNormalFrame()) {
			setNextFrame(new FinalFrame());
			return getNextFrame();
		}
		return this;
	}

	public boolean isLastNormalFrame() {
		return getFrameNumber() == 9;
	}

	@Override
	public FrameResult getFrameResult() {
		return this.frameResult;
	}

	@Override
	public boolean isFinished() {
		return frameResult.isFinished();
	}

	@Override
	Frame self() {
		return this;
	}

	@Override
	public int getScore() {
		Score score = frameResult.getScore();
		if (frameResult.getScore().isScoreCalculateComplete()) {
			return score.getScore();
		}
		try {
			return getNextFrame().calculateScore(score).getScore();
		} catch (Exception e) {
			return FrameScores.NON_CALCULATE_SCORE;
		}
	}

}
