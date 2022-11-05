package bowling.domain.frame.state;

public class Pins {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private int falledPins;

    public Pins(int falledPins) {
        validate(falledPins);
        this.falledPins = falledPins;
    }

    private void validate(int falledPins) {
        if (falledPins < MIN_PINS) {
            throw new IllegalArgumentException("볼링 핀의 갯수는 0 미만이 될 수 없습니다.");
        }

        if (falledPins > MAX_PINS) {
            throw new IllegalArgumentException("볼링 핀의 갯수는 최대 10을 넘을 수 없습니다.");
        }
    }
}
