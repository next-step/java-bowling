package bowling.domain;

import bowling.domain.state.*;
import bowling.exception.EndedFrameException;
import bowling.exception.InvalidNumberOfFallenPinsException;
import bowling.exception.MaximumSumExceededException;

public class Frame {
    public static final int READY = -1;
    public static final int MAX_NUMBER_OF_PIN = 10;

    private final ThrowingState ready;
    private final ThrowingState firstBowl;
    private final ThrowingState gutter;
    private final ThrowingState strike;
    private final ThrowingState secondBowl;
    private final ThrowingState spare;
    private final ThrowingState miss;

    private ThrowingState currentFrameState;
    private int first = READY;
    private int second = READY;

    public Frame() {
        this.ready = new Ready(this);
        this.firstBowl = new FirstBowl(this);
        this.gutter = new Gutter(this);
        this.strike = new Strike();
        this.secondBowl = new SecondBowl(new Ready(this));
        this.spare = new Spare(new Ready(this));
        this.miss = new Miss(new Ready(this));

        currentFrameState = this.ready;
    }

    Frame play(final int round, final int numberOfFallenPins) {
        validate(round, numberOfFallenPins);
        if (currentFrameState instanceof Ready) {
            first = numberOfFallenPins;
            changeState(currentFrameState);
            return this;
        }
        if (currentFrameState instanceof RunningState) {
            second = numberOfFallenPins;
            changeState(currentFrameState);
            return this;
        }

        throw new EndedFrameException(round);
    }

    private void validate(final int round, final int numberOfFallenPins) {
        if (currentFrameState instanceof EndedState) {
            throw new EndedFrameException(round);
        }

        if (numberOfFallenPins <= READY || numberOfFallenPins > MAX_NUMBER_OF_PIN) {
            throw new InvalidNumberOfFallenPinsException(numberOfFallenPins);
        }

        if (!(currentFrameState instanceof Ready) && (currentFrameState instanceof RunningState) && (first + numberOfFallenPins > MAX_NUMBER_OF_PIN)) {
            throw new MaximumSumExceededException(first, numberOfFallenPins);
        }
    }

    private void changeState(ThrowingState throwingState) {
        this.currentFrameState = throwingState.bowl();
    }

    public int first() {
        return first;
    }

    public int second() {
        return second;
    }

    public ThrowingState frameState() {
        return currentFrameState;
    }

    public boolean isEnd() {
        return currentFrameState instanceof EndedState;
    }
}
