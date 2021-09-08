package bowling.domain.common;

import java.util.Objects;

import bowling.domain.common.exception.InvalidPinCountException;
import bowling.domain.score.BaseScore;
import bowling.domain.score.Score;

public class Pins {

	private static final int MIN_HIT_COUNT = 0;
	private static final int MAX_HIT_COUNT = 10;

	private final int hitCount;

	private Pins(final int hitCount) {
		crateValidation(hitCount);
		this.hitCount = hitCount;
	}

	private void crateValidation(final int hitCount) {
		if (hitCount < MIN_HIT_COUNT || hitCount > MAX_HIT_COUNT) {
			throw new InvalidPinCountException();
		}
	}

	public static Pins of(final int pinCount) {
		return new Pins(pinCount);
	}

	public Pins add(final Pins pins) {
		return new Pins(hitCount + pins.hitCount);
	}

	public int getHitCount() {
		return hitCount;
	}

	public boolean isAllHit() {
		return hitCount == MAX_HIT_COUNT;
	}

	public Score score() {
		return BaseScore.of(hitCount);
	}

	public Score spareScore() {
		return BaseScore.of(MAX_HIT_COUNT - hitCount);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Pins))
			return false;
		final Pins pins = (Pins)o;
		return getHitCount() == pins.getHitCount();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getHitCount());
	}
}
