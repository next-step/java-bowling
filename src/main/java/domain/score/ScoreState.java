package domain.score;

public class ScoreState {

	private static final int PHASE_ALIVE_LEFT = -1;

	private final int left;
	private final boolean reflectedPrevious;

	private ScoreState(int left, boolean reflectedPrevious) {
		this.left = left;
		this.reflectedPrevious = reflectedPrevious;
	}

	public static ScoreState of(boolean reflectedPrevious) {
		return new ScoreState(PHASE_ALIVE_LEFT, reflectedPrevious);
	}

	public static ScoreState of (int left, boolean reflectedPrevious) {
		return new ScoreState(left, reflectedPrevious);
	}

	ScoreState initializeLeft(int left) {
		return ScoreState.of(left, reflectedPrevious);
	}

	boolean canReflect() {
		return left != 0;
	}

	boolean canShowScore() {
		return !canReflect() && reflectedPrevious;
	}

	ScoreState minusOneLeft() {
		return ScoreState.of(left - 1, reflectedPrevious);
	}

	ScoreState reflected() {
		return ScoreState.of(left, true);
	}

}
