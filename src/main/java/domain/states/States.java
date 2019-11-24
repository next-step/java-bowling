package domain.states;

import domain.state.State;

public interface States {

	boolean isEndFrame();

	State addNewState(int remainPins);

	boolean shouldRestorePins();

}
