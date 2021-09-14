package bowling.domain.score;

import java.util.Objects;

import bowling.domain.score.exception.InvalidScoreException;

public class ProgressScore extends DefaultScore {

	private static final int COMPUTABLE_LEFT_COUNT = 0;
	private static final int SPARE_LEFT_COUNT = 1;
	private static final int BASE_LEFT_COUNT = 2;

	private final int leftCount;

	private ProgressScore(final int score, final int leftCount) {
		super(score);

		createValidation(leftCount);
		this.leftCount = leftCount;
	}

	private void createValidation(final int leftCount) {
		if (leftCount < COMPUTABLE_LEFT_COUNT || leftCount > BASE_LEFT_COUNT) {
			throw new InvalidScoreException();
		}
	}

	public static ProgressScore ofStrike() {
		return of(MAX_HIT_PINS_COUNT, BASE_LEFT_COUNT);
	}

	public static ProgressScore ofSpare() {
		return of(MAX_HIT_PINS_COUNT, SPARE_LEFT_COUNT);
	}

	public static ProgressScore of(final int score, final int leftCount) {
		return new ProgressScore(score, leftCount);
	}

	@Override
	public Score add(final Score score) {
		final int nextValue = getValue() + score.getValue();
		final int nextLeftCount = this.leftCount - 1;

		if (nextLeftCount == COMPUTABLE_LEFT_COUNT) {
			return ComputableScore.of(nextValue);
		}

		return ProgressScore.of(nextValue, nextLeftCount);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProgressScore))
			return false;
		if (!super.equals(o))
			return false;
		final ProgressScore that = (ProgressScore)o;
		return leftCount == that.leftCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), leftCount);
	}
}
