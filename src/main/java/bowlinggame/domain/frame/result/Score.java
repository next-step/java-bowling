package bowlinggame.domain.frame.result;

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

	public static Score of(int score, Result result) {
		return Score.of(score, result.getBonusCount());
	}

	public static Score of(int score, int bonusCount) {
		return new Score(score, bonusCount);
	}

	public Score calculateBonus(Result result) {
		if (hasBonus()) {
			score += result.getKnockedDownPinCount();
			--bonusCount;
		}
		return this;
	}

	public Score sum(Score totalScore) {
		if (hasBonus()) {
			return this;
		}
		return Score.of(score + totalScore.score);
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
