package bowlinggame.domain.frame;

import java.util.Objects;

public class Score {

	public static final String EMPTY_SCORE_CHARACTER = "";

	private int score;
	private int bonusCount;

	private Score(int score, int bonusCount) {
		this.score = score;
		this.bonusCount = bonusCount;
	}

	public static Score init() {
		return Score.of(0);
	}

	public static Score of(int score) {
		return Score.of(score, 0);
	}

	public static Score of(int score, int bonusCount) {
		return new Score(score, bonusCount);
	}

	public Score calculateBonus(Score score) {
		if (hasBonus()) {
			int total = this.score + score.score;
			return of(total, bonusCount - 1);
		}
		return this;
	}

	public String getScoreCharacter() {
		if (hasBonus()) {
			return EMPTY_SCORE_CHARACTER;
		}
		return String.valueOf(score);
	}

	public boolean hasBonus() {
		return bonusCount > 0;
	}

	public int getScore() {
		return score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Score)) {
			return false;
		}
		Score score1 = (Score) o;
		return score == score1.score &&
				bonusCount == score1.bonusCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score, bonusCount);
	}
}

