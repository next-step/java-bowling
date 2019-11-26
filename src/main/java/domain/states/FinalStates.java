package domain.states;

import domain.state.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinalStates implements States {

	private final static int MIDDLE_PHASE_INDEX = 1;
	private final static int MAX_STATES_SIZE = 3;

	private final List<State> states;

	private FinalStates(List<State> states) {
		this.states = new ArrayList<>(states);
	}

	public static FinalStates of(List<State> states) {
		return new FinalStates(states);
	}

	public static FinalStates newInstance() {
		return new FinalStates(Collections.singletonList(Ready.getInstance()));
	}

	/**
	 * FinalFrame은
	 * 세 번의 state가 있거나 두 번째 State가 Gutter 이거나 Miss이면 끝이다
	 */
	@Override
	public boolean isEndFrame() {
		if (states.isEmpty() || states.size() == 1) {
			return false;
		}

		if (states.size() == MAX_STATES_SIZE) {
			return true;
		}

		return states.get(MIDDLE_PHASE_INDEX).isLastStateInFinalFrame();
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
		return states.get(getLastIndex()).isRestoredState();
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
		FinalStates that = (FinalStates) o;
		return Objects.equals(states, that.states);
	}

	@Override
	public int hashCode() {
		return Objects.hash(states);
	}

}
