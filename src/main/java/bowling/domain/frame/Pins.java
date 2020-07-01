package bowling.domain.frame;

import bowling.domain.status.Pending;
import bowling.domain.status.Status;
import bowling.domain.status.Strike;

public class Pins {
    private static final int MIN_COUNT = 0;
    private static final int MAX_PIN_COUNT = 10;

    private int downPin;

    private Pins(int downPin) {
        this.downPin = downPin;
    }

    public static Pins init() {
        return new Pins(MIN_COUNT);
    }

    public Status bowl(int downPin, Status prevStatus) {
        this.downPin += downPin;
        validateRange(this.downPin);
        return prevStatus.next(downPin);
    }

    public Status firstBowl(int downPin) {
        validateRange(downPin);
        this.downPin = downPin;
        if (downPin == MAX_PIN_COUNT) {
            return new Strike();
        }
        return new Pending(downPin);
    }

    public int getDownPin() {
        return downPin;
    }

    private void validateRange(int downPin) {
        if (downPin < MIN_COUNT || downPin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("투구 값이 유효 범위가 아닙니다.");
        }

    }


}
