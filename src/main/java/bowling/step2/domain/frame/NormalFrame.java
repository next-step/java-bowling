package bowling.step2.domain.frame;

import bowling.step2.domain.status.Ready;
import bowling.step2.domain.status.Status;

import java.util.Objects;

public class NormalFrame implements Frame {
	private static final int MAX_NORMAL_FRAME = 9;

	private final int no;
	private Frame nextFrame;
	private Status status;

	public NormalFrame(int no) {
		this.no = no;
		status = new Ready();
	}

	@Override
	public Frame bowling(int pin) {
		status = status.bowling(pin);
		if (!status.isFinished()) {
			return this;
		}
		return createFrame();
	}

	private Frame createFrame() {
		if (no < MAX_NORMAL_FRAME) {
			nextFrame = new NormalFrame(no + 1);
			return nextFrame;
		}
		nextFrame = new FinalFrame();
		return nextFrame;
	}

	@Override
	public boolean isGameOver() {
		return false;
	}

	@Override
	public void addFrameResult(FrameBoard board) {
		board.add(status.getMark());

		if (!Objects.isNull(nextFrame)) {
			nextFrame.addFrameResult(board);
		}
	}

	@Override
	public FrameBoard makeBoard() {
		FrameBoard board = new FrameBoard();
		addFrameResult(board);
		return board;
	}

	@Override
	public int getFrameNo() {
		if (status.isFinished()) {
			return no + 1;
		}
		return no;
	}
}
