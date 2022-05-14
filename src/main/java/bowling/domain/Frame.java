package bowling.domain;

import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;

public class Frame {
    public static final int NO_PINS_LEFT = 0;
    public static final int MAX_NUMBER_OF_PIN = 10;

    private int numberOfPinsRemaining = 10;
    private int numberOfAttemptsRemaining = 2;

    Frame() {
    }

    int play(final int round, final int numberOfFallenPins) {
        validate(round, numberOfFallenPins);
        numberOfPinsRemaining -= numberOfFallenPins;
        numberOfAttemptsRemaining--;

        return numberOfPinsRemaining;
    }

    private void validate(final int round, final int numberOfFallenPins) {
        if (numberOfPinsRemaining == NO_PINS_LEFT || numberOfAttemptsRemaining == NO_PINS_LEFT) {
            throw new EndedFrameException(round);
        }

        if (numberOfFallenPins < NO_PINS_LEFT || numberOfFallenPins > MAX_NUMBER_OF_PIN) {
            throw new InvalidNumberOfFallenPinsException(numberOfFallenPins);
        }
    }

    void record(Record record) {
        record.record(MAX_NUMBER_OF_PIN - numberOfPinsRemaining);
    }
}
