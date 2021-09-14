package bowling.view;

import java.util.Arrays;

public enum ScoreFlag {

	GUTTER(0, "-"),
	STRIKE(10, "X");

	private final int hitPinCount;
	private final String flag;

	ScoreFlag(final int hitPinCount, final String flag) {
		this.hitPinCount = hitPinCount;
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public static String find(final int number) {
		return Arrays.stream(values())
			.filter(key -> key.hitPinCount == number)
			.findFirst()
			.map(e -> e.flag)
			.orElse(String.valueOf(number));
	}
}
