package bowling.score;

import java.util.Objects;

public class Score {

	public static final int UNAVAILABLE_NOW = -1;

	private final int value;
	private final int bonusCount;

	private Score(int value, int bonusCount) {
		this.value = value;
		this.bonusCount = bonusCount;
	}

	public static Score of(int value, int bonusCount) {
		return new Score(value, bonusCount);
	}

	public static Score open(Score first, Score second) {
		return new Score(first.value + second.value, 0);
	}

	public static Score spare(Score maxScore) {
		return new Score(maxScore.value, 1);
	}

	public static Score strike(Score score) {
		return new Score(score.value, 2);
	}

	public boolean canScore() {
		return bonusCount == 0;
	}

	public int getValue() {
		return value;
	}

	public Score accumulate(Score operand) {
		return new Score(value + operand.value, this.bonusCount - 1);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Score score = (Score)o;
		return value == score.value && bonusCount == score.bonusCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, bonusCount);
	}
}
