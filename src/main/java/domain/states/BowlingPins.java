package domain.states;

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

}
