package bowling.util;

public enum ScoreBound {
	MINIMUM_SCORE_BOUND(0),
	MAXIMUM_SCORE_BOUND(10);

	private int bound;

	ScoreBound(int bound) {
		this.bound = bound;
	}

	public int getBound() {
		return bound;
	}
}
