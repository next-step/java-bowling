package bowlinggame.domain.frame;

import bowlinggame.domain.frame.state.FinalState;

public class FinalFrame implements Frame {

	private FinalState state = new FinalState();

	@Override
	public Frame next() {
		throw new IllegalStateException("마지막 프레임입니다.");
	}

	@Override
	public Frame roll(int pinCount) {
		if (isCompleted()) {
			return this;
		}

		state.roll(pinCount);
		return this;
	}

	@Override
	public Score getScore() {
		return state.getScore();
	}

	@Override
	public Score calculateBonus(Score score) {
		return state.calculateBonus(score);
	}

	@Override
	public boolean isCompleted() {
		return state.isFinished();
	}

	@Override
	public boolean isSameNumber(FrameNumber frameNumber) {
		return FrameNumber.last() == frameNumber;
	}

	@Override
	public FrameResult getFrameResult() {
		Score score = getScore();
		if (isCompleted() && !score.hasBonus()) {
			return new FrameResult(state.getResult(), score.getScore());
		}
		return new FrameResult(state.getResult(), FrameResult.UNSCORE);
	}
}
