package bowling.domain.frame;

import bowling.domain.DownedPinCount;
import bowling.domain.state.InitState;
import bowling.domain.state.State;

import java.util.Objects;

public class Frame {

	private static final int INCREMENTAL = 1;
	private static final int FINAL_INDEX = 10;

	private final int index;

	private State state;

	public Frame(int index) {
		this.index = index;
		this.state = InitState.getInstance();
	}

	public boolean isFrameFinished() {
		return state.isDone();
	}

	public Frame roll(DownedPinCount downedPinCount) {
		state = state.roll(downedPinCount);
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

	private Frame getNextFrame() {
		int nextIndex = index + INCREMENTAL;
		if(isNextFinal(nextIndex)) {
			return new FinalFrame(nextIndex);
		}
		return new Frame(nextIndex);
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
