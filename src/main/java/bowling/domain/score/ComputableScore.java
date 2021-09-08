package bowling.domain.score;

import bowling.domain.score.exception.InvalidScoreException;

public class ComputableScore extends DefaultScore {

	private ComputableScore(final int score) {
		super(score);
	}

	public static ComputableScore of(final int score) {
		return new ComputableScore(score);
	}

	public static ComputableScore of(final Score score) {
		return new ComputableScore(score.getValue());
	}

	public static ComputableScore ofZero() {
		return of(MIN);
	}

	@Override
	public boolean isComputeAble() {
		return true;
	}

	@Override
	public Score add(final Score score) {
		throw new InvalidScoreException();
	}
}
