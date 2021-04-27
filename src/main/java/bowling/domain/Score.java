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
		if (hitPin + previousHitPin == 10 && playCount == 1) {
			return Score.STRIKE;
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

	public String mark() {
		if (mark.isEmpty()) {
			return String.valueOf(hitPin);
		}
		return mark;
	}
}
