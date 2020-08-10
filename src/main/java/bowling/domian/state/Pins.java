package bowling.domian.state;

import bowling.domian.state.exception.InvalidPinCountException;

public class Pins {
    private static int MIN_PIN_COUNT = 0;
    private static int MAX_PIN_COUNT = 10;

    private int falledPinCount;

    private Pins(int falledPinCount) {
        this.falledPinCount = falledPinCount;
    }

    public static Pins bowl(int pinCount) {
        if (pinCount < MIN_PIN_COUNT || pinCount > MAX_PIN_COUNT) {
            throw new InvalidPinCountException();
        }

        return new Pins(pinCount);
    }

    public Pins secondBowl(int pinCount) {
        if (pinCount < MIN_PIN_COUNT || pinCount > MAX_PIN_COUNT) {
            throw new InvalidPinCountException();
        }

        if (this.falledPinCount + pinCount > MAX_PIN_COUNT) {
            throw new InvalidPinCountException();
        }

        return new Pins(pinCount);
    }

    public boolean isStrike() {
        return this.falledPinCount == MAX_PIN_COUNT;
    }

    public boolean isSpare(Pins secondPins) {
        return this.falledPinCount + secondPins.falledPinCount == MAX_PIN_COUNT;
    }

    public boolean isMiss(Pins secondPins) {
        return this.falledPinCount + secondPins.falledPinCount < MAX_PIN_COUNT;
    }
}
