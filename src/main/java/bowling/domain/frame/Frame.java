package bowling.domain.frame;

import bowling.domain.DownedPinCount;
import bowling.domain.score.Score;
import bowling.domain.state.InitState;
import bowling.domain.state.State;

import java.util.Objects;

public class Frame {

	private static final int INCREMENTAL = 1;
	private static final int FINAL_INDEX = 10;
	private static final Frame GROUND_FRAME = new Frame();

	private final int index;
	protected State state;
	protected Frame previous;

	private Frame() {
		this.index = 0;
		this.state = InitState.getInstance();
	}

	public Frame(int index) {
		this.index = index;
		this.state = InitState.getInstance();
		this.previous = GROUND_FRAME;
	}

	public Frame(int index, Frame previous) {
		this.index = index;
		this.state = InitState.getInstance();
		this.previous = previous;
	}

	public boolean isFrameFinished() {
		return state.isDone();
	}

	public Frame roll(DownedPinCount downedPinCount) {
		addScoreToPrevious(downedPinCount);
		state = state.roll(downedPinCount, previous.getScore());
		if(isFrameFinished()) {
			return getNextFrame();
		}
		return this;
	}

	public int getFrameSequence() {
		return index;
	}

	public State getState() {
		return state;
	}

	public Score getScore() {
		return state.getScore();
	}

	protected void addScoreToPrevious(DownedPinCount pinCount) {
		if(previous != GROUND_FRAME) {
			previous.addScore(pinCount);
			previous.addScoreToPrevious(pinCount);
		}
	}

	private void addScore(DownedPinCount pinCount) {
		state.addPreviousCount(pinCount);
	}

	private Frame getNextFrame() {
		int nextIndex = index + INCREMENTAL;
		if(isNextFinal(nextIndex)) {
			return new FinalFrame(nextIndex, this);
		}
		return new Frame(nextIndex, this);
	}

	private boolean isNextFinal(int nextIndex) {
		return nextIndex == FINAL_INDEX;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Frame frame = (Frame) o;
		return index == frame.index;
	}

	@Override
	public int hashCode() {
		return Objects.hash(index);
	}

}
