package bowling.domain.common;

import java.util.Objects;

import bowling.domain.exception.InvalidPinCountException;

public final class Pins {

	private static final int MIN_COUNT = 0;
	private static final int MAX_COUNT = 10;

	private final int count;

	private Pins(final int count) {
		this.count = count;
	}

	public static Pins of(final int count) {
		validateCreate(count);
		return new Pins(count);
	}

	private static void validateCreate(final int count) {
		if (count < MIN_COUNT || count > MAX_COUNT) {
			throw new InvalidPinCountException();
		}
	}

	public int count() {
		return count;
	}

	public int sumCount(final Pins pins) {
		return sumCount(pins.count);
	}

	public int sumCount(final int count) {
		return this.count + count;
	}

	public boolean isEqualsCount(final Pins pins) {
		return isEqualsCount(pins.count);
	}

	public boolean isEqualsCount(final int count) {
		return this.count == count;
	}

	public boolean isBiggerThan(final Pins pins) {
		return isBiggerThan(pins.count);
	}

	public boolean isBiggerThan(final int count) {
		return this.count > count;
	}

	public boolean isSmallerThan(final Pins pins) {
		return isSmallerThan(pins.count);
	}

	public boolean isSmallerThan(final int count) {
		return this.count < count;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Pins))
			return false;
		final Pins that = (Pins)o;
		return count == that.count;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count);
	}
}
