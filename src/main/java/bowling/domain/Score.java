package bowling.domain;

public class Score {

	private final int score;

	private Score(int score) {
		this.score = score;
	}

	public static Score ofScore(int score) {
		validateScore(score);
		return new Score(score);
	}

	private static void validateScore(int score) {
		final int MINIMUM_SCORE_BOUND = 0;
		final int MAXIMUM_SCORE_BOUND = 10;

		if (score < MINIMUM_SCORE_BOUND || score > MAXIMUM_SCORE_BOUND) {
			throw new IllegalArgumentException("first and second frames score have more than 10 scores. wrong score.");
		}
	}

	public Score add(Score score) {
		return Score.ofScore(this.score + score.getScore());
	}

	public int getScore() {
		return score;
	}

	public boolean isGreaterThan(Score second) {
		return this.score > second.getScore();
	}
}
