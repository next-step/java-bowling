package bowling.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PitchResult {

	STRIKE("X", Collections.singletonList(10)),
	SPARE("/", Collections.emptyList()),
	MISS("", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)),
	GUTTER("-", Collections.singletonList(0));

	private static final int SPARE_NUMBER = 10;

	private final String flag;
	private final List<Integer> pinCounts;

	PitchResult(final String flag, final List<Integer> pinCounts) {
		this.flag = flag;
		this.pinCounts = pinCounts;
	}

	public String getFlag(final int pinCount) {
		return this.equals(MISS) ? String.valueOf(pinCount) : flag;
	}

	public static PitchResult findByPinCount(final int first, final int second) {
		if (first + second == SPARE_NUMBER) {
			return SPARE;
		}

		return findByPinCount(second);
	}

	public static PitchResult findByPinCount(final int pinCount) {
		return Arrays.stream(PitchResult.values())
			.filter(pitchResult -> {
				final List<Integer> pinCounts = pitchResult.pinCounts;
				return pinCounts.contains(pinCount);
			})
			.findFirst()
			.orElseGet(() -> MISS);
	}
}
