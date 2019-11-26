package domain.frame_states;

import domain.state.State;
import domain.BowlingPins;
import domain.states.States;

public interface FrameStates {

	State roll(BowlingPins fallenPins);

	boolean isEndFrame();

	States getStates();

}
