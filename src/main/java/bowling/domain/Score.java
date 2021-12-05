package bowling.domain;

import java.util.Objects;

import bowling.exception.CannotCalculateException;

public class Score {
	public static final int INCALCULABLE_SCORE = -1;
	public static final int INCALCULABLE_LEFT = -1;

	public static final int NO_LEFT = 0;

	private final int score;
	private final int left;

	private Score(int score, int left) {
		this.score = score;
		this.left = left;
	}

	public static Score create(int score, int left) {
		return new Score(score, left);
	}

	public static Score create(int score) {
		return create(score, NO_LEFT);
	}

	public static Score createIncalculableScore() {
		return create(INCALCULABLE_SCORE, INCALCULABLE_LEFT);
	}

	public Score bowl(Score score) {
		int sum = this.score + score.score;
		return create(sum, left - 1);
	}

	public int getScore() {
		if (!canCalculateScore()) {
			throw new CannotCalculateException();
		}
		return this.score;
	}

	public int getScoreWithoutException() {
		return this.score;
	}

	public boolean canCalculateScore() {
		return left == NO_LEFT;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Score score1 = (Score)obj;

		if (score != score1.score) {
			return false;
		}
		return left == score1.left;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score, left);
	}

	@Override
	public String toString() {
		return "Score{" +
			"score=" + score +
			", left=" + left +
			'}';
	}
}
