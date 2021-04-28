package bowling.domain;

public enum Score {
	STRIKE(10, "X"),
	SPARE(10, "/"),
	GUTTER(0, "-"),
	MISS(-1, "");

	private final int hitPin;
	private final String mark;

	Score(int hitPin, String mark) {
		this.hitPin = hitPin;
		this.mark = mark;
	}

	public static Score score(int hitPin, int previousHitPin, int playCount) {
		return Score.score(hitPin, previousHitPin, 0 ,playCount);
	}
	public static Score score(int hitPin, int previousHitPin, int firstHitSize, int playCount) {
		if (playCount==3) {
			return finalFrame(hitPin, previousHitPin, firstHitSize);
		}
		if (hitPin + previousHitPin == 10 && playCount == 1) {
			return Score.STRIKE;
		}

		if (previousHitPin == 10 && hitPin == 0  && playCount > 1) {
			return Score.GUTTER;
		}

		if (hitPin + previousHitPin == 10 && playCount > 1) {
			return Score.SPARE;
		}

		if (hitPin == 10 && playCount > 1) {
			return Score.STRIKE;
		}

		if (hitPin == 0) {
			return Score.GUTTER;
		}

		return Score.MISS;
	}

	private static Score finalFrame(int hitPin, int previousHitPin, int firstHitSize) {
		if (firstHitSize + previousHitPin != 10 && hitPin + previousHitPin == 10) {
			return Score.SPARE;
		}
		if (firstHitSize + previousHitPin == 10 && hitPin != 10) {
			return Score.MISS;
		}
		if (firstHitSize + previousHitPin == 10 && hitPin == 10) {
			return Score.STRIKE;
		}
		if (hitPin == 0) {
			return Score.GUTTER;
		}
		return Score.MISS;
	}

	public String mark() {
		if (mark.isEmpty()) {
			return String.valueOf(hitPin);
		}
		return mark;
	}
}
