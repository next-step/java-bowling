package bowlinggame.domain.frame;

import bowlinggame.domain.frame.result.Score;

public interface Frame {

	static Frame first() {
		return of(FrameNumber.first());
	}

	static Frame of(FrameNumber frameNumber) {
		if (frameNumber.isLast()) {
			return new FinalFrame();
		}
		return new NormalFrame(frameNumber);
	}

	Frame next();
	Frame roll(int pinCount);
	Score getScore();
	Score calculateBonus(Score score);
	boolean isCompleted();
	boolean isSameNumber(FrameNumber frameNumber);
	FrameResult getFrameResult();
}
