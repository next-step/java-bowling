package bowlinggame.domain.frame;

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
	boolean isCompleted();
	boolean isSameNumber(FrameNumber frameNumber);
	FrameResult result();
}
