package bowling.domain;

import bowling.domain.state.Ready;
import bowling.domain.state.ThrowingState;
import bowling.exception.InvalidNumberOfFallenPinsException;

public class Bowl {
    public static final int READY = -1;
    public static final int MAX_NUMBER_OF_PIN = 10;

    private ThrowingState throwingState;
    private int numberOfFallenPins;

    // 1st initialize
    public Bowl(Frame frame) {
        throwingState = new Ready(frame);
        numberOfFallenPins = READY;
    }

    // 2nd initialize
    public Bowl(ThrowingState throwingState) {
        this.throwingState = throwingState;
        numberOfFallenPins = READY;
    }

    public void bowl(final int numberOfFallenPins) {
        validate(numberOfFallenPins);
        this.numberOfFallenPins = numberOfFallenPins;
        throwingState = throwingState.bowl(numberOfFallenPins);
    }

    private void validate(final int numberOfFallenPins) {
        if (numberOfFallenPins <= READY || numberOfFallenPins > MAX_NUMBER_OF_PIN) {
            throw new InvalidNumberOfFallenPinsException(numberOfFallenPins);
        }
    }

    public int pins() {
        return numberOfFallenPins;
    }

    public ThrowingState state() {
        return throwingState;
    }

    public boolean complete() {
        return numberOfFallenPins != READY;
    }
}
