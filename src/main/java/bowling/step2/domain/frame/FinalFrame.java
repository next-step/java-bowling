package bowling.step2.domain.frame;

import bowling.step2.domain.status.*;

import java.util.Objects;

public class FinalFrame implements Frame {
	private Status status;
	private Status bonusStatus;
	private boolean gameOver = false;

	public FinalFrame() {
		status = new Ready();
	}

	@Override
	public Frame bowling(int pin) {
		status = status.bowling(pin);

		gameOver = false;
		if (!status.isFinished()) {
			return this;
		}
		if (!Objects.isNull(bonusStatus)) {
			status = bonusStatus.bowling(pin);
		}
		if (status instanceof Strike || status instanceof Spare) {
			bonusStatus = new Bonus(status.getMark());
			return this;
		}
		gameOver = true;

		return this;
	}

	@Override
	public boolean isGameOver() {
		return gameOver;
	}

	@Override
	public void addFrameResult(FrameBoard board) {
		board.add(status.getMark());
	}

	@Override
	public FrameBoard makeBoard() {
		FrameBoard board = new FrameBoard();
		addFrameResult(board);
		return board;
	}

	@Override
	public int getFrameNo() {
		return 10;
	}
}
