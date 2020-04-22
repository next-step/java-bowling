package bowling.domain.pin;

import bowling.exception.BowlingException;

import java.util.Objects;
import java.util.Optional;

public class Pins {

    private final Pin firstPin;
    private final Pin secondPin;

    public Pins(Pin firstPin, Pin secondPin) {
        validateTotalPinCount(firstPin, secondPin);
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public static Pins from() {
        return new Pins(new Pin(Pin.MAX_PIN), null);
    }

    private void validateTotalPinCount(Pin firstPin, Pin secondPin) {
        int second = Optional.ofNullable(secondPin)
                .map(pin -> pin.getDownPin())
                .orElse(0);
        if (firstPin.getDownPin() + second > Pin.MAX_PIN) {
            throw new BowlingException();
        }
    }


    public Pins addSecondPin(Pin second) {
        if (secondPin != null) {
            throw new BowlingException();
        }
        return new Pins(firstPin, second);
    }

    public boolean isFinish() {
        return getDownPins() == Pin.MAX_PIN
                || secondPin != null;
    }

    public boolean isRunning() {
        return !isFinish() && secondPin == null;
    }

    public boolean isStrike() {
        return isFinish() && firstPin.isFinish();
    }

    public boolean isSpare() {
        return isFinish() && !isStrike() && getDownPins() == Pin.MAX_PIN;
    }

    public boolean isMiss() {
        return isFinish() && !isStrike() && !isSpare() && !isGutter();
    }

    public boolean isGutter() {
        return firstPin.isGutter() && Optional.ofNullable(secondPin)
                .map(pin -> pin.isGutter())
                .orElse(false);
    }

    public int getDownPins() {
        return firstPin.getDownPin() + Optional.ofNullable(secondPin)
                .map(pin -> pin.getDownPin())
                .orElse(0);
    }

    public int getFirstDownPin() {
        return firstPin.getDownPin();
    }

    public int getSecondDownPin() {
        return secondPin.getDownPin();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return Objects.equals(firstPin, pins.firstPin) &&
                Objects.equals(secondPin, pins.secondPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPin, secondPin);
    }
}
