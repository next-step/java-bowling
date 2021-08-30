package bowling.domain.common;

import java.util.Objects;

import bowling.domain.common.exception.InvalidCaculateScoreException;
import bowling.domain.pitch.Pitch;

public class Score {

	private static final int MAX_SCORE = 30;
	private static final int MIN_SCORE = 0;
	private static final int MAX_LEFT_COUNT = 3;
	private static final int MIN_LEFT_COUNT = 0;

	private final int value;
	private final int leftCount;

	public Score(final int score, final int leftCount) {
		validateScore(score);
		validateLeft(leftCount);

		this.value = score;
		this.leftCount = leftCount;
	}

	public static Score strike() {
		return new Score(10, 2);
	}

	public static Score spare() {
		return new Score(10, 1);
	}

	public static Score miss(final int score) {
		return new Score(score, 0);
	}

	private void validateScore(final int score) {
		if (score > MAX_SCORE || score < MIN_SCORE) {
			throw new InvalidCaculateScoreException();
		}
	}

	private void validateLeft(final int left) {
		if (left > MAX_LEFT_COUNT || left < MIN_LEFT_COUNT) {
			throw new InvalidCaculateScoreException();
		}
	}

	public boolean possiblecalculate() {
		return leftCount == MIN_LEFT_COUNT;
	}

	public Score plus(final Pitch pitch) {
		return new Score(value + pitch.getFalledPins().count(), leftCount - 1);
	}

	public int getValue() {
		if (!possiblecalculate()) {
			throw new InvalidCaculateScoreException();
		}
		return value;
	}

	public int getLeftCount() {
		return leftCount;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Score score1 = (Score)o;
		return value == score1.value && leftCount == score1.leftCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, leftCount);
	}
}
