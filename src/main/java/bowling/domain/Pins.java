package bowling.domain;

import bowling.domain.exception.PinsCountException;

import java.util.Objects;

public class Pins {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;

    private final int pins;

    private Pins(int pitch) {
        validatePitch(pitch);
        this.pins = pitch;
    }

    private void validatePitch(int pitch) {
        if (pitch < MIN_PINS || pitch > MAX_PINS) {
            throw new PinsCountException();
        }
    }

    public static Pins ofFirstPitch(int firstPitch) {
        return new Pins(firstPitch);
    }

    public Pins ofSecondPitch(int secondPitch) {
        validatePitch(this.pins + secondPitch);
        return new Pins(secondPitch);
    }

    public int pins(){
        return this.pins;
    }

    public boolean isSpare(Pins secondPins){
        if(pins() + secondPins.pins() == MAX_PINS){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pins)) return false;
        Pins pins = (Pins) o;
        return pins() == pins.pins();
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
