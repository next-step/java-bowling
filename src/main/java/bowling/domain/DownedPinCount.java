package bowling.domain;

import bowling.exception.BowlingException;

import java.util.Objects;

public class DownedPinCount {

	public static final DownedPinCount NO_PIN_DOWN = new DownedPinCount(0);
	public static final DownedPinCount ALL_PIN_DOWN = new DownedPinCount(10);

	private static final int MIN_DOWN_COUNT = 0;
	private static final int MAX_DOWN_COUNT = 10;
	private static final String INVALID_COUNT = "쓰러진 핀 수는 0 이상 10 이하의 자연수입니다. 입력 된 수 : %d";

	private final int count;

	private DownedPinCount(int count) {
		this.count = count;
	}

	public static DownedPinCount fromDownCount(int count) {
		if(count < MIN_DOWN_COUNT || count > MAX_DOWN_COUNT) {
			throw new BowlingException(String.format(INVALID_COUNT, count));
		}
		return new DownedPinCount(count);
	}

	public static DownedPinCount sum(DownedPinCount one, DownedPinCount theOther) {
		int sum = one.count + theOther.count;
		return new DownedPinCount(sum);
	}

	@Override
	public String toString() {
		return String.valueOf(count);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DownedPinCount that = (DownedPinCount) o;
		return count == that.count;
	}

	@Override
	public int hashCode() {
		return Objects.hash(count);
	}
}
