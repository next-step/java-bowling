package bowling.score;

import java.util.Objects;

import bowling.util.Validator;

public class Score {

	private static final int UNAVAILABLE_SCORE = -1;
	private static final int UNAVAILABLE_COUNT = -1;

	private static final int MIN_SCORE = 0;

	private static final int MIN_BONUS_COUNT = 0;
	private static final int SPARE_BONUS_COUNT = 1;
	private static final int STRIKE_BONUS_COUNT = 2;

	private final int value;
	private final int bonusCount;

	private Score(int value, int bonusCount) {
		this.value = value;
		this.bonusCount = bonusCount;
	}

	public static Score unavailable() {
		return new Score(UNAVAILABLE_SCORE, UNAVAILABLE_COUNT);
	}

	public static Score remain(Score first) {
		return Score.of(first.value, MIN_BONUS_COUNT);
	}

	public static Score open(Score first, Score second) {
		return Score.of(first.value + second.value, MIN_BONUS_COUNT);
	}

	public static Score spare(Score score) {
		return Score.of(score.value, SPARE_BONUS_COUNT);
	}

	public static Score strike(Score score) {
		return Score.of(score.value, STRIKE_BONUS_COUNT);
	}

	public static Score of(int value, int bonusCount) {
		Validator.min(MIN_SCORE, value,
			String.format("점수의 최솟값(%d) 보다 입력(%d)이 작습니다.", MIN_SCORE, value));
		Validator.min(MIN_BONUS_COUNT, value,
			String.format("보너스 카운트 최솟값(%d) 보다 입력(%d)이 작습니다.", MIN_BONUS_COUNT, value));
		return new Score(value, bonusCount);
	}

	public boolean canScore() {
		if (!isAvailable()) {
			return false;
		}
		return bonusCount == 0;
	}

	private boolean isAvailable() {
		return value != UNAVAILABLE_SCORE && bonusCount != UNAVAILABLE_COUNT;
	}

	public Score accumulate(Score operand) {
		return new Score(value + operand.value, this.bonusCount - 1);
	}

	public int getValue() {
		return value;
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
