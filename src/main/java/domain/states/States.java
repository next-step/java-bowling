package domain.states;

public interface States {

	boolean isEndFrame();

	void addNewState(int remainPins);

	boolean shouldRestorePins();

}
