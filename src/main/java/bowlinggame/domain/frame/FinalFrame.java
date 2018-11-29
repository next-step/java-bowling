package bowlinggame.domain.frame;

public class FinalFrame implements Frame {

	private static final int MAX_ROLL_OPPORTUNITY = 3;

	private FrameResult frameResult;

	public FinalFrame() {
		this.frameResult = new FrameResult();
	}

	@Override
	public Frame next() {
		throw new IllegalStateException("마지막 프레임입니다.");
	}

	@Override
	public Frame roll(int pinCount) {
		if (isCompleted()) {
			return this;
		}

		int knockedDownPinCount = changeToMinCount(frameResult.getTotalKnockedDownPinCount());
		Pin pin = Pin.fromKnockedPinCount(knockedDownPinCount);
		frameResult.record(pin.knockDown(pinCount));
		return this;
	}

	private int changeToMinCount(int totalKnockedDownPinCount) {
		return totalKnockedDownPinCount % Pin.MAX_COUNT;
	}

	@Override
	public boolean isCompleted() {
		if (isFinishedRolling(FinalFrame.MAX_ROLL_OPPORTUNITY)) {
			return true;
		}
		if (isFinishedRolling(NormalFrame.MAX_ROLL_OPPORTUNITY)
				&& !frameResult.isAllKnockedDown()) {
			return true;
		}
		return false;
	}

	private boolean isFinishedRolling(int maxRollOpportunity) {
		return frameResult.isSameRollOpportunity(maxRollOpportunity);
	}

	@Override
	public boolean isSameNumber(FrameNumber frameNumber) {
		return FrameNumber.last() == frameNumber;
	}

	@Override
	public FrameResult result() {
		return frameResult;
	}
}
