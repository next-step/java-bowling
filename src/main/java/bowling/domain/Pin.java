package bowling.domain;

public class Pin {
    private final int pins;

    public Pin(final int pins) {
        this.pins = validatePinNumber(pins);
    }

    private int validatePinNumber(final int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException("숫자의 범위는 0과 10사이에 있어야합니다.");
        }
        return pins;
    }
}
