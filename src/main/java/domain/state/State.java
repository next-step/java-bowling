package domain.state;

public interface State {

	State nextState(int fallenPinsCount);

	default boolean isLastState() {
		return false;
	}

	default boolean isRestoredState() {
		return false;
	}

}
