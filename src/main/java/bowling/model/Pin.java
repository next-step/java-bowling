package bowling.model;

import java.util.Objects;

public class Pin {

	private static final String POSITIVE_ERROR_MESSAGE = "핀은 양수만 가능 합니다.";
	private static final String MAX_OVER_ERROR_MESSAGE = "최대 핀의 개수는 10개 입니다.";
	private static final int MIN_PIN = 0;
	private static final int MAX_PIN = 10;

	private final int pin;

	public Pin(int pin) {
		checkPositive(pin);
		checkMaxPinOver(pin);
		this.pin = pin;
	}

	private void checkMaxPinOver(int pin) {
		if (pin > MAX_PIN) {
			throw new IllegalArgumentException(MAX_OVER_ERROR_MESSAGE);
		}
	}

	private void checkPositive(int pin) {
		if (pin < MIN_PIN) {
			throw new IllegalArgumentException(POSITIVE_ERROR_MESSAGE);
		}
	}

	public int getPin() {
		return pin;
	}

	public boolean isMaxPin() {
		return pin == MAX_PIN;
	}

	public Pin add(Pin secondPin) {
		checkMaxPinOver((this.pin + secondPin.getPin()));
		return new Pin(this.pin + secondPin.getPin());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Pin pin1 = (Pin)o;
		return pin == pin1.pin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pin);
	}

}
