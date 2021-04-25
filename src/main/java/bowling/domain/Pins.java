package bowling.domain;

import bowling.domain.exception.PinsCountException;

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
        validatePitch(secondPitch);
        return new Pins(this.pins + secondPitch);
    }

    public int pins(){
        return this.pins;
    }
}
