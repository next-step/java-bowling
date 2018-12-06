package bowlinggame.domain.frame;

import bowlinggame.domain.frame.state.Ready;
import bowlinggame.domain.frame.state.State;
import java.util.Objects;

public class NormalFrame implements Frame {

	private FrameNumber frameNumber;
	private State state;
	private Frame nextFrame;

	public NormalFrame(FrameNumber frameNumber) {
		this(frameNumber, new Ready());
	}

	public NormalFrame(FrameNumber frameNumber, State state) {
		this.frameNumber = frameNumber;
		this.state = state;
	}

	@Override
	public Frame next() {
		if (nextFrame == null) {
			nextFrame = Frame.of(frameNumber.next());
		}
		return nextFrame;
	}

	@Override
	public Frame roll(int pinCount) {
		if (isCompleted()) {
			return next().roll(pinCount);
		}

		state = state.roll(pinCount);
		return this;
	}

	@Override
	public Score getScore() {
		Score score = state.getScore();
		return delegateNextFrame(score);
	}

	@Override
	public Score calculateBonus(Score score) {
		score = state.calculateBonus(score);
		return delegateNextFrame(score);
	}

	private Score delegateNextFrame(Score score) {
		if (score.hasBonus()) {
			return next().calculateBonus(score);
		}
		return score;
	}

	@Override
	public boolean isCompleted() {
		return state.isFinished();
	}

	@Override
	public boolean isSameNumber(FrameNumber frameNumber) {
		return this.frameNumber == frameNumber;
	}

	@Override
	public FrameResult getFrameResult() {
		Score score = getScore();
		if (isCompleted() && !score.hasBonus()) {
			return new FrameResult(state.getResult(), score.getScore());
		}
		return new FrameResult(state.getResult(), FrameResult.UNSCORE);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof NormalFrame)) {
			return false;
		}
		NormalFrame that = (NormalFrame) o;
		return Objects.equals(frameNumber, that.frameNumber);
	}

	@Override
	public int hashCode() {
		return Objects.hash(frameNumber);
	}
}
