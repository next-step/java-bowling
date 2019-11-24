package domain.frame_states;

import domain.state.State;
import domain.states.BowlingPins;

public interface FrameStates {

	State roll(BowlingPins fallenPins);

}
