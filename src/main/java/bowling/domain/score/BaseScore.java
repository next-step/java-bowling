package bowling.domain.score;

public class BaseScore extends DefaultScore {

	private BaseScore(final int score) {
		super(score);
	}

	public static BaseScore ofStrike() {
		return of(MAX_HIT_PINS_COUNT);
	}

	public static BaseScore ofZero() {
		return of(MIN);
	}

	public static BaseScore of(final int score) {
		return new BaseScore(score);
	}

	@Override
	public boolean isComputeAble() {
		return false;
	}

	@Override
	public Score add(final Score score) {
		return new BaseScore(getValue() + score.getValue());
	}
}
