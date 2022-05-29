package bowling.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import bowling.state.Initialized;
import bowling.state.Open;
import bowling.state.State;

public class EndFrame implements Frame {

	private final List<State> states = new ArrayList<>();
	private int tryCount;

	public EndFrame() {
		this.states.add(Initialized.of());
		this.tryCount = 0;
	}

	@Override
	public boolean isEnd() {
		if (tryCount == 3) {
			return true;
		}
		return tryCount == 2 && lastState() instanceof Open;
	}

	@Override
	public void throwBowl(int throwCount) {

		tryCount++;

		State lastState = lastState();
		if (lastState.isEnd()) {
			addState(throwCount);
			return;
		}

		updateCurrentState(throwCount);
	}

	private void addState(int throwCount) {
		states.add(Initialized.of().throwBowl(throwCount));
	}

	private void updateCurrentState(int throwCount) {
		State lastState = lastState();
		states.remove(lastState);
		states.add(lastState.throwBowl(throwCount));
	}

	private State lastState() {
		return this.states.get(this.states.size() - 1);
	}

	@Override
	public Optional<Frame> nextFrame() {
		return Optional.empty();
	}

	@Override
	public int number() {
		return MAX_FRAME_NUMBER;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EndFrame endFrame = (EndFrame)o;
		return tryCount == endFrame.tryCount && Objects.equals(states, endFrame.states);
	}

	@Override
	public int hashCode() {
		return Objects.hash(states, tryCount);
	}
}
