package bowling.domain.frame;

import java.util.Objects;

import bowling.domain.Index;
import bowling.domain.Pins;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

public class NormalFrame implements Frame {
	private final Index index;
	private State state;

	private NormalFrame(Index index, State state) {
		this.index = index;
		this.state = state;
	}

	public static NormalFrame create(Index index, State state) {
		return new NormalFrame(index, state);
	}

	public static NormalFrame create(Index index) {
		return create(index, Ready.create());
	}

	public static NormalFrame initialize() {
		return create(Index.first());
	}

	@Override
	public Frame bowl(Pins pins) {
		this.state = state.bowl(pins);

		if (state.isEnd()) {
			return createNextFrame();
		}
		return this;
	}

	private Frame createNextFrame() {
		if (index.next().isMax()) {
			return LastFrame.initialize();
		}
		return create(index.next());
	}

	@Override
	public boolean isEnd() {
		return state.isEnd();
	}

	public int getFrameIndex() {
		return index.getValue();
	}

	@Override
	public String symbol() {
		return state.symbol();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		NormalFrame frame = (NormalFrame)obj;

		return Objects.equals(index, frame.index);
	}

	@Override
	public int hashCode() {
		return Objects.hash(index);
	}
}
