package bowling.step2.domain;

public class Score {
	private static final int INIT_SCORE = 0;

	private int sumScore;

	public Score(int sumScore) {
		this.sumScore = sumScore;
	}

	public static Score init() {
		return new Score(INIT_SCORE);
	}
}
