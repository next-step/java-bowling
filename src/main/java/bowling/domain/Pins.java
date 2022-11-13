package bowling.domain;

import java.util.List;

public class Pins {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;
    private List<Pin> pins;

    public Pins() {
        this(PinGenerator.generate());
    }

    public Pins(List<Pin> pins) {
        this.pins = pins;
    }

    public void knockDown(int knockedDownPins) {
        validPins(knockedDownPins);

        pins.stream()
                .filter(Pin::isStanding)
                .limit(knockedDownPins)
                .forEach(Pin::knockDown);
    }

    private void validPins(int knockedDownPins) {
        currentValidPind(knockedDownPins);
        validOverPins(knockedDownPins);
    }

    private void currentValidPind(int knockedDownPins) {
        if (knockedDownPins > MAX_PINS) {
            throw new IllegalArgumentException(
                    String.format("볼링 핀은 최대 10을 넘을 수 없습니다. 현재 쓰러진 핀 수는 %d", knockedDownPins));
        }

        if (knockedDownPins < MIN_PINS) {
            throw new IllegalArgumentException(
                    String.format("볼링 핀은 최초 0 미만이 될 수 없습니다. 현재 쓰러진 핀 수는 %d", knockedDownPins));
        }
    }

    private void validOverPins(int knockedDownPins) {
        if (knockedDownPins > standingPinCount()) {
            throw new IllegalArgumentException(
                    String.format("남아 있는 볼링 핀수[%d]보다 클 수 없습니다. 현재 쓰러진 핀 수는 [%d]", standingPinCount(), knockedDownPins));
        }
    }

    public int standingPinCount() {
        return (int) pins.stream()
                .filter(Pin::isStanding)
                .count();
    }

    public void add(List<Pin> addPins) {
        pins.addAll(addPins);
    }
}
