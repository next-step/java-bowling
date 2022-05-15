package bowling.domain;

import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;

public class Frame {
    public static final int BEFORE_BOWLING = -1;
    public static final int MAX_NUMBER_OF_PIN = 10;

    private int first = BEFORE_BOWLING;
    private int second = BEFORE_BOWLING;

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
        if (first == MAX_NUMBER_OF_PIN || second != -1) {
            throw new EndedFrameException(round);
        }

        if (numberOfFallenPins <= BEFORE_BOWLING || numberOfFallenPins > MAX_NUMBER_OF_PIN) {
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
