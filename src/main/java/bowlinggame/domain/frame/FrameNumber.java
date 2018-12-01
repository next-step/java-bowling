package bowlinggame.domain.frame;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.IntStream;

public class FrameNumber {

	public static final int FIRST = 1;
	public static final int LAST = 10;
	private static final Map<Integer, FrameNumber> CACHE = IntStream
			.rangeClosed(FIRST, LAST)
			.boxed()
			.collect(toMap(Function.identity(), number -> new FrameNumber(number)));

	private int number;

	private FrameNumber(int number) {
		this.number = number;
	}

	public static FrameNumber first() {
		return FrameNumber.of(FIRST);
	}

	public static FrameNumber of(int number) {
		if (number < FIRST || number > LAST) {
			throw new IllegalArgumentException("잘못된 프레임번호입니다.");
		}
		return CACHE.get(Integer.valueOf(number));
	}

	public static FrameNumber last() {
		return FrameNumber.of(LAST);
	}

	public FrameNumber next() {
		if (isLast()) {
			return this;
		}
		return FrameNumber.of(number + 1);
	}

	public boolean isLast() {
		return number == LAST;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof FrameNumber)) {
			return false;
		}
		FrameNumber that = (FrameNumber) o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
