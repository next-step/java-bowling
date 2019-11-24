package domain.states;

import domain.state.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NormalStates implements States {

	private final List<State> states;
	private final BowlingPins pins;

	private NormalStates(List<State> states, BowlingPins pins) {
		this.states = states;
		this.pins = pins;
	}

	public static NormalStates newInstance() {
		return new NormalStates(new ArrayList<>(), BowlingPins.newInstance());
	}

	public static NormalStates of(int pins, State... states) {
		return new NormalStates(new ArrayList<>(Arrays.asList(states)), BowlingPins.of(pins));
	}

	@Override
	public void roll(BowlingPins fallenPins) {

	}

}
