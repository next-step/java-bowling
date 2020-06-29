package bowling.domain;

public class Pin {

    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private int maxPins;
    private int falledPins;

    public Pin() {
        this.falledPins = MIN_PINS;
        this.maxPins = MAX_PINS;
    }

    public Pin(int maxPins, int falledPins) {
        validPins(maxPins, falledPins);
        this.maxPins = maxPins;
        this.falledPins = falledPins;
    }


    public boolean isAllClear() {
        return maxPins - falledPins == 0;

    }

    public int leftPins() {
        return maxPins - falledPins;
    }

    public boolean isGutter() {
        return maxPins - falledPins == maxPins;
    }

    public boolean isMiss() {
        return maxPins - falledPins != 0;
    }

    private void validPins(int pins) {
        if (pins < MIN_PINS || pins > MAX_PINS) {
            throw new IllegalArgumentException();
        }
    }

    private void validPins(int maxPins, int falledPins) {
        if (falledPins < MIN_PINS || falledPins > maxPins) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(falledPins);
    }

}
