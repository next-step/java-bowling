package domain.states;

import domain.state.State;

import java.util.List;

public interface States {

	boolean isEndFrame();

	State addNewState(int remainPins);

	boolean shouldRestorePins();

	List<String> getPhaseResultSign();

}
