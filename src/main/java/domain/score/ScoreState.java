package domain.score;

public class ScoreState {

	private int left;
	private boolean reflectedPrevious;

	private ScoreState(int left, boolean reflectedPrevious) {
		this.left = left;
		this.reflectedPrevious = reflectedPrevious;
	}

	public static ScoreState of(int left, boolean reflectedPrevious) {
		return new ScoreState(left, reflectedPrevious);
	}

	void initializeLeft(int left) {
		this.left = left;
	}

	boolean canReflect() {
		return left != 0;
	}

	boolean canShowScore() {
		return !canReflect() && reflectedPrevious;
	}

	void minusOneLeft() {
		left--;
	}

	void reflected() {
		reflectedPrevious = true;
	}

}
