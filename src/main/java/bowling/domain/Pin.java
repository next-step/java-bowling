package bowling.domain;

import java.util.Objects;

public class Pin {
	public static final int MAX_PIN_COUNT = 10;
	private final int count;

	public Pin(int count){
		this.validate(count);
		this.count = count;
	}

	private void validate(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("0보다 작을 수 없습니다.");
		}

		if (count > MAX_PIN_COUNT) {
			throw new IllegalArgumentException("10보다 클 수 없습니다.");
		}
	}

	public Pin next(int nextCount) {
		if (this.count + nextCount > MAX_PIN_COUNT) {
			throw new IllegalArgumentException("10보다 클 수 없습니다.");
		}

		return new Pin(nextCount);
	}

	public boolean isEnd() {
		return count == MAX_PIN_COUNT;
	}

	public int getCount() {
		return count;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Pin pin = (Pin)o;
		return count == pin.count;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count);
	}
}
