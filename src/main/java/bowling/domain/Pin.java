package bowling.domain;

public class Pin {

    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;
    private int maxPins;
    private int fallenPins;

    public Pin() {
        this.fallenPins = MIN_PINS;
        this.maxPins = MAX_PINS;
    }

    public Pin(int maxPins, int fallenPins) {
        validPins(maxPins, fallenPins);
        this.maxPins = maxPins;
        this.fallenPins = fallenPins;
    }

    public boolean isAllClear() {
        return maxPins - fallenPins == 0;
    }

    public int leftPins() {
        return maxPins - fallenPins;
    }

    public int fallenPins() {
        return fallenPins;
    }

    public boolean isGutter() {
        return maxPins - fallenPins == maxPins;
    }

    public boolean isMiss() {
        return maxPins - fallenPins != 0;
    }

    private void validPins(int pins) {
        if (pins < MIN_PINS || pins > MAX_PINS) {
            throw new IllegalArgumentException();
        }
    }

    private void validPins(int maxPins, int fallenPins) {
        if (fallenPins < MIN_PINS || fallenPins > maxPins) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(fallenPins);
    }

}
