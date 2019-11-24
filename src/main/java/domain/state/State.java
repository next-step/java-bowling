package domain.state;

public interface State {

	State nextState(int fallenPinsCount);

	default boolean isLastStateInFinalFrame() {
		return false;
	}

	default boolean isRestoredState() {
		return false;
	}

	int getFallenBowlingPins();

	default boolean isLastStateToDecideScoreLeft() {
		return false;
	}

	default int getLeftStatesToCalculateScore() {
		return 0;
	}

}
