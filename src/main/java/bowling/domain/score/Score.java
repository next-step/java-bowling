package bowling.domain.score;

import bowling.util.ScoreBound;

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
		if (score < ScoreBound.MINIMUM_SCORE_BOUND.getBound() || score > ScoreBound.MAXIMUM_SCORE_BOUND.getBound()) {
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

	public boolean isScoreTen() {
		return score == ScoreBound.MAXIMUM_SCORE_BOUND.getBound();
	}

	public boolean isScoreZero() {
		return score == ScoreBound.MINIMUM_SCORE_BOUND.getBound();
	}
}
