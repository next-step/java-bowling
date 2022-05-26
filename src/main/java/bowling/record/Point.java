package bowling.record;

import java.util.Objects;

import bowling.util.Validator;

public class Point {

	private static final int MIN_THROW_COUNT = 0;
	private static final int MAX_THROW_COUNT = 10;

	private final int value;

	public Point(int throwCount) {
		Validator.min(MIN_THROW_COUNT, throwCount,
			String.format("투구 기록이 최솟값(%d) 보다 작습니다. 입력 : %d", MIN_THROW_COUNT, throwCount));
		Validator.max(MAX_THROW_COUNT, throwCount,
			String.format("투구 기록이 최댓값(%d) 보다 큽니다. 입력 : %d", MAX_THROW_COUNT, throwCount));

		this.value = throwCount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Point point = (Point)o;
		return value == point.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value);
	}
}
