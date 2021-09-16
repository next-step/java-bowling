package step3.domain;

import java.util.Objects;
import step3.exceptions.PinNumberExecption;

public class Pins {

    private static final int MAX = 10;
    private static final int MIN = 0;

    private int fallenPins;

    public Pins() {
        this(MAX);
    }

    public Pins(int numOfPin) {
        this.fallenPins = numOfPin;
    }

    public Pins bowl(int fallenPins) {
        this.fallenPins -= fallenPins;
        validPin();
        return this;
    }

    private void validPin() {
        if (fallenPins < MIN || fallenPins > MAX) {
            throw new PinNumberExecption();
        }
    }

    public int getFallenPins() {
        return fallenPins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pins pins = (Pins) o;
        return fallenPins == pins.fallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }

    public Score sumScore(Score beforeScore) {
        return beforeScore.bowl(fallenPins);
    }

    public boolean isSpair(int secondFalledPins) {
        return fallenPins + secondFalledPins == MAX;
    }
}
