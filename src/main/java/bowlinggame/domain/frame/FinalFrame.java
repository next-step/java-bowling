package bowlinggame.domain.frame;

import bowlinggame.domain.result.Spare;
import bowlinggame.domain.result.Strike;

public class FinalFrame extends Frame {

	private static final int MAX_ROLL_OPPORTUNITY = 3;

	public FinalFrame(int number) {
		super(number);
	}

	@Override
	public Frame roll(Pin pin) {
		if (isCompleted()) {
			throw new IllegalStateException("마지막 프레임입니다.");
		}
		if (!pin.canRecord(getRemainingPin()) && !getRemainingPin().isStandAll()) {
			throw new IllegalArgumentException("남은 핀보다 많은 개수를 던질 수 없습니다.");
		}
		record(pin);
		return this;
	}

	@Override
	public boolean isCompleted() {
		if (isSameRollOpportunity(MAX_ROLL_OPPORTUNITY)) {
			return true;
		}
		if (isSameRollOpportunity(NormalFrame.MAX_ROLL_OPPORTUNITY) && !isStrikeOrSpare()) {
			return true;
		}
		return false;
	}

	private boolean isStrikeOrSpare() {
		return isSameLastResult(Strike.getInstance()) || isSameLastResult(Spare.getInstance());
	}
}
