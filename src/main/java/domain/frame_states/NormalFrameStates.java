package domain.frame_states;

import domain.state.State;
import domain.states.BowlingPins;
import domain.states.NormalStates;
import domain.states.States;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class NormalFrameStates implements FrameStates {

	private final States states;
	private final BowlingPins pins;

	private NormalFrameStates(BowlingPins pins) {
		this(NormalStates.newInstance(), pins);
	}

	private NormalFrameStates(States states, BowlingPins pins) {
		this.states = states;
		this.pins = pins;
	}

	public static NormalFrameStates newInstance() {
		return new NormalFrameStates(BowlingPins.newInstance());
	}

	public static NormalFrameStates of(int pins, State... states) {
		return new NormalFrameStates(NormalStates.of(new ArrayList<>(Arrays.asList(states))),
				BowlingPins.of(pins));
	}

	@Override
	public void roll(BowlingPins fallenPins) {
		int fallenPinsCount = pins.roll(fallenPins);
		states.addNewState(fallenPinsCount);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NormalFrameStates that = (NormalFrameStates) o;
		return Objects.equals(states, that.states) &&
				Objects.equals(pins, that.pins);
	}

	@Override
	public int hashCode() {
		return Objects.hash(states, pins);
	}

}
