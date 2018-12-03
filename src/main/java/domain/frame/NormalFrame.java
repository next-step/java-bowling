package domain.frame;

import domain.FrameScores;
import domain.Pin;
import domain.Score;
import domain.frame.state.State;
import domain.frame.state.None;

/**
 * Created by hspark on 30/11/2018.
 */
public class NormalFrame extends AbstractFrame {
	private State state;

	public NormalFrame(int frameNumber) {
		super(frameNumber);
		this.state = new None();
	}

	@Override
	public Frame pitch(Pin pin) {
		if (state.isFinished()) {
			return getNextFrame().pitch(pin);
		}
		this.state = state.tryBowl(pin);
		return next();
	}

	public Frame next() {
		if (state.isFinished() && !isLastNormalFrame()) {
			setNextFrame(new NormalFrame(getFrameNumber() + 1));
			return getNextFrame();
		}
		if (state.isFinished() && isLastNormalFrame()) {
			setNextFrame(new FinalFrame());
			return getNextFrame();
		}
		return this;
	}

	public boolean isLastNormalFrame() {
		return getFrameNumber() == 9;
	}

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public boolean isFinished() {
		return state.isFinished();
	}

	@Override
	public Frame self() {
		return this;
	}

	@Override
	public int getScore() {
		Score score = state.getScore();
		if (state.getScore().isScoreCalculateComplete()) {
			return score.getScore();
		}
		try {
			return getNextFrame().calculateScore(score).getScore();
		} catch (Exception e) {
			return FrameScores.NON_CALCULATE_SCORE;
		}
	}

}
