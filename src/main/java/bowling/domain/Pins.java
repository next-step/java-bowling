package bowling.domain;

import bowling.exception.PinsRangeException;

public class Pins {
	public static final int MIN_OF_PINS = 0;
	public static final int MAX_OF_PINS = 10;

	private final int value;

	public Pins(int value) {
		validateRange(value);
		this.value = value;
	}

	private void validateRange(int value) {
		if (value < MIN_OF_PINS || value > MAX_OF_PINS) {
			throw new PinsRangeException();
		}
	}

	public static Pins create(int value) {
		return new Pins(value);
	}

	public boolean isStrike() {
		return value == MAX_OF_PINS;
	}

	public boolean isSpare(Pins other) {
		return value + other.value == MAX_OF_PINS;
	}

	public boolean isGutter() {
		return value == MIN_OF_PINS;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Pins pins = (Pins)obj;

		return value == pins.value;
	}

	@Override
	public int hashCode() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}
}
