package bowling.score;

import bowling.global.exception.InputPinsNullPointerException;
import bowling.global.exception.OutOfPinsRangeException;
import bowling.global.utils.ExceptionMessage;
import bowling.global.utils.StringParser;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class Pin {

    private static final int PINS_MIN_VALUE = 0;
    private static final int PINS_MAX_VALUE = 10;

    private int falledPins;

    private Pin(int falledPins) {
        this.falledPins = falledPins;
        validateOutofPinsRange();
    }

    public static Pin bowl(String falledPins) {
        validatePinsIsNull(falledPins);
        return new Pin(new StringParser(falledPins).toInt());
    }

    public boolean isStrike() {
        return this.falledPins == PINS_MAX_VALUE;
    }

    public boolean isSpare(int falledPins) {
        return this.falledPins + falledPins == PINS_MAX_VALUE;
    }

    public boolean isGutter() {
        return this.falledPins == PINS_MIN_VALUE;
    }

    public int getFalledPins() {
        return falledPins;
    }

    private static void validatePinsIsNull(String score) {
        if (Strings.isBlank(score)) {
            throw new InputPinsNullPointerException(ExceptionMessage.INVALID_PITCH_BALL_IS_NULL);
        }
    }

    private void validateOutofPinsRange() {
        if (falledPins < PINS_MIN_VALUE || falledPins > PINS_MAX_VALUE) {
            throw new OutOfPinsRangeException(ExceptionMessage.INVALID_PITCH_RANGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return falledPins == pin.falledPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(falledPins);
    }
}
