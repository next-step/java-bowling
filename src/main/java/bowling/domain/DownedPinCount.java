package bowling.domain;

import bowling.exception.BowlingException;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DownedPinCount {
	ZERO(0),
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10);

	private static final String INVALID_COUNT = "쓰러진 핀 수는 0 이상 10 이하의 자연수입니다. 입력 된 수 : %d";
	private static final Map<Integer, DownedPinCount> valuesMap = Arrays.stream(DownedPinCount.values())
																.collect(Collectors.toUnmodifiableMap(downedPinCount -> downedPinCount.count, Function.identity()));

	private final int count;

	DownedPinCount(int count) {
		this.count = count;
	}

	public static DownedPinCount from(int count) {
		return Optional.ofNullable(valuesMap.get(count))
				.orElseThrow(() -> new BowlingException(String.format(INVALID_COUNT, count)));
	}

	public static int sumIntValue(DownedPinCount one, DownedPinCount theOther) {
		int sum = one.count + theOther.count;
		return sum;
	}

	@Override
	public String toString() {
		return String.valueOf(count);
	}
}
