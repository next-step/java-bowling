package bowlinggame.domain.frame;

import java.util.Objects;

public class NormalFrame implements Frame {

	public static final int MAX_ROLL_OPPORTUNITY = 2;

	private FrameNumber frameNumber;
	private FrameResult frameResult;
	private Frame nextFrame;

	public NormalFrame(FrameNumber frameNumber) {
		this(frameNumber, new FrameResult());
	}

	public NormalFrame(FrameNumber frameNumber, FrameResult frameResult) {
		this.frameNumber = frameNumber;
		this.frameResult = frameResult;
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

		Pin pin = Pin.fromKnockedPinCount(frameResult.getTotalKnockedDownPinCount());
		frameResult.record(pin.knockDown(pinCount));
		return this;
	}

	@Override
	public boolean isCompleted() {
		return frameResult.isSameRollOpportunity(MAX_ROLL_OPPORTUNITY)
				|| frameResult.isAllKnockedDown();
	}

	@Override
	public boolean isSameNumber(FrameNumber frameNumber) {
		return this.frameNumber == frameNumber;
	}

	@Override
	public FrameResult result() {
		return frameResult;
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
