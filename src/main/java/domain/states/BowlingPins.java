package domain.states;

import java.util.Objects;

public class BowlingPins {

	@SuppressWarnings("FieldCanBeLocal")
	private static int MIN_BOWLING_PINS = 0;
	@SuppressWarnings("FieldCanBeLocal")
	private static int MAX_BOWLING_PINS = 10;

	private int pins;

	private BowlingPins(int pins) {
		this.pins = pins;
	}

	public static BowlingPins newInstance() {
		return new BowlingPins(MAX_BOWLING_PINS);
	}

	public static BowlingPins of(int pins) {
		if (pins > MAX_BOWLING_PINS || pins < MIN_BOWLING_PINS) {
			throw new IllegalArgumentException(String.format("볼링핀의 개수는 %s이상 %s이하입니다",
					MIN_BOWLING_PINS, MAX_BOWLING_PINS));
		}
		return new BowlingPins(pins);
	}

	public int roll(BowlingPins fallenPins) {
		if (pins - fallenPins.pins < MIN_BOWLING_PINS) {
			throw new IllegalArgumentException(String.format("존재하는 볼링핀(%s) 보다 많은 볼링핀(%s)을 쓰러뜨릴 수 없습니다",
					pins, fallenPins));
		}
		pins -= fallenPins.pins;
		return fallenPins.pins;
	}

	public void restore() {
		pins = 10;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BowlingPins that = (BowlingPins) o;
		return pins == that.pins;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pins);
	}

}
