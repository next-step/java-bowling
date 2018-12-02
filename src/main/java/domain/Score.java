package domain;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 02/12/2018.
 */
public class Score {
	public static final Score STRIKE = new Score(10, 2);
	public static final Score SPARE = new Score(10, 1);

	private int score;
	private int leftCalculateCount;

	private Score(int score, int leftCalculateCount) {
		this.score = score;
		this.leftCalculateCount = leftCalculateCount;
	}

	public static Score of(int score) {
		return new Score(score, 0);
	}

	public Score calculate(Pin pin) {
		return new Score(score + pin.toInteger(), leftCalculateCount - 1);
	}

	public int getScore() {
		checkArgument(isScoreCalculateComplete());
		return this.score;
	}

	public int toInteger() {
		return this.score;
	}

	public boolean isScoreCalculateComplete() {
		return leftCalculateCount == 0;
	}
}
