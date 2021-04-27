package bowling.domain.pin;

import bowling.domain.frame.FrameStatus;

import java.util.Objects;

public final class FinalPins implements Pins {

    private final NormalPins pins;
    private final Pin thirdPin;

    public FinalPins() {
        this(null, null, null);
    }

    public FinalPins(Pin firstPin) {
        this(firstPin, null, null);
    }

    public FinalPins(Pin firstPin, Pin secondPin) {
        this(firstPin, secondPin, null);
    }

    public FinalPins(Pin firstPin, Pin secondPin, Pin thirdPin) {
        this(new NormalPins(firstPin, secondPin), thirdPin);
    }

    public FinalPins(NormalPins pins, Pin thirdPin) {
        this.pins = pins;
        this.thirdPin = thirdPin;
    }

    @Override
    public Pin firstPin() {
        return pins.firstPin();
    }

    @Override
    public Pin secondPin() {
        return pins.secondPin();
    }

    @Override
    public FrameStatus frameStatus() {
        return FrameStatus.of(firstPin(), secondPin());
    }

    @Override
    public Pins knockDownPin(Pin pin) {
        return null;
    }

    public Pin thirdPin() {
        return thirdPin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalPins finalPins = (FinalPins) o;
        return Objects.equals(pins, finalPins.pins) && Objects.equals(thirdPin, finalPins.thirdPin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins, thirdPin);
    }
}
