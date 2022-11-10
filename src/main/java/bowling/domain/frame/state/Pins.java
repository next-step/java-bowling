package bowling.domain.frame.state;

public class Pins {
    private static final int MIN_PINS = 0;
    private static final int MAX_PINS = 10;

    private final int falledPins;

    public Pins(int falledPins) {
        validate(falledPins);
        this.falledPins = falledPins;
    }

    public boolean isStrike() {
        return falledPins == MAX_PINS;
    }

    public boolean isSpare(Pins secondPins) {
        if (falledPins == MAX_PINS) {
            return false;
        }

        return this.falledPins + secondPins.falledPins == MAX_PINS;
    }

    public Pins add(int falledPins) {
        return new Pins(this.falledPins + falledPins);
    }

    public boolean isLegalPins(Pins secondsPins) {
        return falledPins + secondsPins.falledPins <= MAX_PINS;
    }

    public int getPins() {
        return falledPins;
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
