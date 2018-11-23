package bowlinggame.domain.frame;

import bowlinggame.domain.result.Spare;
import bowlinggame.domain.result.Strike;

public class NormalFrame extends Frame {

	public static final int MAX_ROLL_OPPORTUNITY = 2;

	public NormalFrame(int number) {
		super(number);
	}

	@Override
	public Frame roll(Pin pin) {
		if (!pin.canRecord(getRemainingPin())) {
			throw new IllegalArgumentException("남은 핀보다 많은 개수를 던질 수 없습니다.");
		}

		record(pin);
		if (isCompleted()) {
			return next();
		}
		return this;
	}

	@Override
	public boolean isCompleted() {
		return isStrikeOrSpare() || isSameRollOpportunity(MAX_ROLL_OPPORTUNITY);
	}

	private boolean isStrikeOrSpare() {
		return isSameLastResult(Strike.getInstance()) || isSameLastResult(Spare.getInstance());
	}
}
