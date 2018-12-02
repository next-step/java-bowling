package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by hspark on 22/11/2018.
 */
public final class Pin {
	private static final String GUTTER_STR = "-";
	private static Map<Integer, Pin> CACHE = new HashMap<>();
	public static final Pin ZERO = Pin.of(0);
	public static final Pin TEN = Pin.of(10);

	private final int score;

	private Pin(int score) {
		this.score = score;
	}

	public static Pin of(int scoreValue) {
		checkArgument(scoreValue <= 10);
		checkArgument(scoreValue >= 0);
		Pin cachePin = CACHE.get(scoreValue);
		if (Objects.nonNull(cachePin)) {
			return cachePin;
		}
		cachePin = new Pin(scoreValue);
		CACHE.put(scoreValue, cachePin);
		return cachePin;
	}

	public Pin add(Pin pin) {
		return Pin.of(toInteger() + pin.toInteger());
	}

	public Pin sub(Pin pin) {
		return Pin.of(toInteger() - pin.toInteger());
	}

	public boolean loe(Pin pin) {
		return this.toInteger() <= pin.toInteger();
	}

	public boolean isZero() {
		return this.equals(Pin.ZERO);
	}

	public boolean isTen() {
		return this.equals(Pin.TEN);
	}

	public int toInteger() {
		return this.score;
	}

	@Override
	public String toString() {
		if (this == Pin.ZERO) {
			return GUTTER_STR;
		}
		return String.valueOf(score);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Pin pin1 = (Pin) o;

		return score == pin1.score;
	}

	@Override
	public int hashCode() {
		return score;
	}
}
