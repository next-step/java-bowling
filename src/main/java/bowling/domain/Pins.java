package bowling.domain;

public class Pins {

    private static final int MINIMUM_PINS = 0;
    private static final int MAXIMUM_PINS = 10;
    private static final Pins DEFAULT_PINS = new Pins(MINIMUM_PINS);

    private final int pins;

	public Pins() {
		this(MINIMUM_PINS);
	}

    public Pins(int pins) {
        valid(pins);
        this.pins = pins;
    }

    public static Pins defaultPins() {
        return DEFAULT_PINS;
    }

    public boolean isMaxPins() {
        return pins == MAXIMUM_PINS;
    }

    private void valid(int pins) {
        if (pins < MINIMUM_PINS) {
            throw new IllegalArgumentException(String.format("핀의 수는 %d 개 보다 작을 수 없습니다.", MINIMUM_PINS));
        }

        if (pins > MAXIMUM_PINS) {
            throw new IllegalArgumentException(String.format("핀의 수는 %d 개 보다 클 수 없습니다.", MAXIMUM_PINS));
        }
    }

}
