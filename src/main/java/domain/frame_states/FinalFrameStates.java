package domain.frame_states;

import domain.state.State;
import domain.states.BowlingPins;
import domain.states.FinalStates;
import domain.states.States;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FinalFrameStates implements FrameStates {

	private final States states;
	private final BowlingPins pins;

	private FinalFrameStates(BowlingPins pins) {
		this(FinalStates.newInstance(), pins);
	}

	private FinalFrameStates(States states, BowlingPins pins) {
		this.states = states;
		this.pins = pins;
	}

	public static FinalFrameStates newInstance() {
		return new FinalFrameStates(BowlingPins.newInstance());
	}

	public static FinalFrameStates of(int pins, State... states) {
		return new FinalFrameStates(FinalStates.of(new ArrayList<>(Arrays.asList(states))),
				BowlingPins.of(pins));
	}

	@Override
	public void roll(BowlingPins fallenPins) {
		validateFrame();
		int fallenPinsCount = pins.roll(fallenPins);
		states.addNewState(fallenPinsCount);
		restorePinsIfNotEnd();
	}

	private void validateFrame() {
		if (states.isEndFrame()) {
			throw new IllegalStateException("끝난 Frame 입니다");
		}
	}

	private void restorePinsIfNotEnd() {
		if (states.shouldRestorePins()) {
			pins.restore();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FinalFrameStates that = (FinalFrameStates) o;
		return Objects.equals(states, that.states) &&
				Objects.equals(pins, that.pins);
	}

	@Override
	public int hashCode() {
		return Objects.hash(states, pins);
	}

}
