package step2.domain;

public class Pins {

    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private final int fallingPins;

    private Pins(int fallingPins) {
        validatePins(fallingPins);
        this.fallingPins = fallingPins;
    }

    public static Pins from(int fallingPins) {
        return new Pins(fallingPins);
    }

    private void validatePins(int fallingPins) {
        validateBelowZero(fallingPins);
        validateOverTen(fallingPins);
    }

    private void validateBelowZero(int fallingPins) {
        if(fallingPins < MIN_PINS) {
            throw new IllegalArgumentException("쓰러진 볼링 핀은 0개 이상이어야 합니다.");
        }
    }

    private void validateOverTen(int fallingPins) {
        if(fallingPins > MAX_PINS) {
            throw new IllegalArgumentException("쓰러진 볼링 핀은 10개 이하여야 합니다.");
        }
    }

    public boolean isStrike() {
        return fallingPins == MAX_PINS;
    }

    public boolean isSpare(Pins pins) {
        int totalPins = fallingPins + pins.getFallingPins();
        return totalPins == MAX_PINS;
    }

    public int getFallingPins() {
        return fallingPins;
    }
}
