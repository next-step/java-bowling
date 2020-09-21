package bowling.step2.domain.frame;

import bowling.step2.domain.status.Status;

public class FinalFrame implements Frame {
	private Status status;

	@Override
	public Frame bowling(int pin) {
		if (!status.isFinished()) {
			status = status.bowling(pin);
			return this;
		}
		return null;
	}

	@Override
	public boolean isGameOver() {
		return true;
	}

	@Override
	public void addFrameResult(FrameBoard board) {

	}

	@Override
	public FrameBoard makeBoard() {
		return null;
	}

	@Override
	public int getFrameNo() {
		return 10;
	}
}
