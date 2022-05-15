package bowling.domain;

import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;

public class Frame {
    public static final int NO_PINS_LEFT = 0;
    public static final int MAX_NUMBER_OF_PIN = 10;

    private int first = -1;
    private int second = -1;

    Frame() {
    }

    int play(final int round, final int numberOfFallenPins) {
        validate(round, numberOfFallenPins);
        if (first == -1) {
            first = numberOfFallenPins;
            return first;
        }
        second = numberOfFallenPins;
        return second;
    }

    private void validate(final int round, final int numberOfFallenPins) {
        if (first == 10 || second != -1) {
            throw new EndedFrameException(round);
        }

        if (numberOfFallenPins < NO_PINS_LEFT || numberOfFallenPins > MAX_NUMBER_OF_PIN) {
            throw new InvalidNumberOfFallenPinsException(numberOfFallenPins);
        }
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
