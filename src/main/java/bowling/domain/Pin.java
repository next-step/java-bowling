package bowling.domain;

public class Pin {

	private static final int MINIMUM_PINS = 0;
	private static final int MAXIMUM_PINS = 10;

	private final int pins;

	public Pin(int pins) {
		valid(pins);
		this.pins = pins;
	}

	private void valid(int pins) {
		if (pins <MINIMUM_PINS) {
			throw new IllegalArgumentException(String.format("핀의 수는 %d 개 보다 작을 수 없습니다.", MINIMUM_PINS));
		}

		if (pins > MAXIMUM_PINS) {
			throw new IllegalArgumentException(String.format("핀의 수는 %d 개 보다 클 수 없습니다.", MAXIMUM_PINS));
		}
	}

}
