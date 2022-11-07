package bowling;

public class Pins {

    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private int falledPins;

    public Pins(int falledPins) {
        validPins(falledPins);
        this.falledPins = falledPins;
    }

    private void validPins(int falledPins) {
        if (falledPins > MAX_PINS) {
            throw new IllegalArgumentException(
                String.format("볼링 핀은 최대 10을 넘을 수 없습니다. 현재 쓰러진 핀 수는 %d", falledPins));
        }

        if (falledPins < MIN_PINS) {
            throw new IllegalArgumentException(
                String.format("볼링 핀은 최초 0 미만이 될 수 없습니다. 현재 쓰러진 핀 수는 %d", falledPins));
        }
    }

    public int totalPins(int secondPins) {
        int totalPins = falledPins + secondPins;
        validPins(totalPins);
        return totalPins;
    }
}
