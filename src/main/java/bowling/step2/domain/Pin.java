package bowling.step2.domain;

public class Pin {
	private static final int MIN_PIN = 0;
	private static final int MAX_PIN = 10;
	private int pin;

	public Pin(int pin) {
		this.pin = pin;
	}

	public static Pin bowlPin(int pin) {
		validPinCount(pin);
		return new Pin(pin);
	}

	private static void validPinCount(int pin) {
		if (pin > MAX_PIN || pin < MIN_PIN) {
			throw new IllegalArgumentException("쓰러트릴 수 있는 핀은 최소 " + MIN_PIN + "개, 최대 " + MAX_PIN + "개 입니다.");
		}
	}

	public boolean isStrike() {
		return this.pin == MAX_PIN;
	}

	public boolean isSpare(int secondPin) {
		if (this.pin + secondPin > MAX_PIN) {
			throw new IllegalArgumentException("쓰러트릴 수 있는 핀은 최대 " + MAX_PIN + "개 입니다.");
		}
		return this.pin + secondPin == MAX_PIN;
	}

	public boolean isGutter() {
		return this.pin == MIN_PIN;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pin pin = (Pin) o;

		return this.pin == pin.pin;
	}

	@Override
	public int hashCode() {
		return pin;
	}

	@Override
	public String toString() {
		return this.isGutter() ? "-" : Integer.toString(pin);
	}
}
