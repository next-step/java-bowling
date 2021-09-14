package bowling.domain.score;

import java.util.Objects;

import bowling.domain.score.exception.InvalidScoreException;

public abstract class DefaultScore implements Score {

	protected static final int MIN = 0;
	protected static final int MAX = 30;
	protected static final int MAX_HIT_PINS_COUNT = 10;

	private final int value;

	protected DefaultScore(final int score) {
		createValidation(score);
		this.value = score;
	}

	private void createValidation(final int score) {
		if (score < MIN || score > MAX) {
			throw new InvalidScoreException();
		}
	}

	public boolean isComputeAble() {
		return false;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DefaultScore))
			return false;
		final DefaultScore that = (DefaultScore)o;
		return getValue() == that.getValue();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getValue());
	}

	@Override
	public int getValue() {
		return value;
	}
}
