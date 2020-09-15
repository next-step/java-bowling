package bowling.domain.frame;

import bowling.domain.DownedPinCount;
import bowling.domain.state.InitState;
import bowling.domain.state.State;

import java.util.Objects;

public class Frame {

	private static final int SEQUENCE_ADDER = 1;

	private final int index;

	private State state;

	public Frame(int index) {
		this.index = index;
		this.state = InitState.getInstance();
	}

	public Frame roll(DownedPinCount downedPinCount) {
		state = state.roll(downedPinCount);
		if(state.isDone()) {
			return new Frame(index + SEQUENCE_ADDER);
		}
		return this;
	}

	public int getFrameSequence() {
		return index + SEQUENCE_ADDER;
	}

	public State getState() {
		return state;
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

	public boolean isFrameFinished() {
		return state.isDone();
	}
}
