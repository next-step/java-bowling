package bowlinggame.domain.frame;

import bowlinggame.domain.frame.result.Results;
import bowlinggame.domain.frame.result.Score;

public class FinalFrame implements Frame {

	private static final int MAX_ROLL_OPPORTUNITY = 3;

	private Results results;

	public FinalFrame() {
		this.results = new Results();
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

		int knockedDownPinCount = changeToMinCount(results.getTotalKnockedDownPinCount());
		Pin pin = Pin.fromKnockedPinCount(knockedDownPinCount);
		results.record(pin.knockDown(pinCount));
		return this;
	}

	@Override
	public Score getScore() {
		return Score.of(results.getTotalKnockedDownPinCount(), 0);
	}

	@Override
	public Score calculateBonus(Score score) {
		return results.addScore(score);
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
				&& !results.isAllKnockedDown()) {
			return true;
		}
		return false;
	}

	private boolean isFinishedRolling(int maxRollOpportunity) {
		return results.isSameRollOpportunity(maxRollOpportunity);
	}

	@Override
	public boolean isSameNumber(FrameNumber frameNumber) {
		return FrameNumber.last() == frameNumber;
	}

	@Override
	public FrameResult getFrameResult() {
		if (isCompleted()) {
			return new FrameResult(results.getDisplayResults(), getScore());
		}
		return new FrameResult(results.getDisplayResults());
	}
}
