package domain.states;

import domain.state.Ready;
import domain.state.State;
import domain.state.Strike;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NormalStates implements States {

	private static final int MAX_STATES_SIZE = 2;

	private final List<State> states;

	private NormalStates(List<State> states) {
		this.states = new ArrayList<>(states);
	}

	public static NormalStates of(List<State> states) {
		return new NormalStates(states);
	}

	public static NormalStates newInstance() {
		return new NormalStates(Collections.singletonList(Ready.getInstance()));
	}

	@Override
	public boolean isEndFrame() {
		return !notRolling() && (isMaxState() || isStrike());
	}

	private boolean notRolling() {
		return states.isEmpty();
	}

	private boolean isMaxState() {
		return states.size() == MAX_STATES_SIZE;
	}

	private boolean isStrike() {
		return states.equals(Collections.singletonList(Strike.getInstance()));
	}

	@Override
	public State addNewState(int fallenPinsCount) {
		State nexState = getNextState(fallenPinsCount);
		deleteReadyState();
		states.add(nexState);
		return nexState;
	}

	private State getNextState(int fallenPinsCount) {
		return states.get(getLastIndex()).nextState(fallenPinsCount);
	}

	private int getLastIndex() {
		return states.size() - 1;
	}

	private void deleteReadyState() {
		states.remove(Ready.getInstance());
	}

	@Override
	public boolean shouldRestorePins() {
		return false;
	}

	@Override
	public List<String> getPhaseResultSign() {
		return states.stream()
				.map(State::toSign)
				.collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NormalStates that = (NormalStates) o;
		return Objects.equals(states, that.states);
	}

	@Override
	public int hashCode() {
		return Objects.hash(states);
	}

}
