package bowling.domain;

import bowling.domain.state.ThrowingState;
import bowling.exception.EndedFrameException;
import bowling.exception.MaximumSumExceededException;

public class Frame {
    private Bowl first;
    private Bowl second;

    public Frame() {
        this.first = new Bowl(this);
        this.second = new Bowl(this);
    }

    Frame play(final int round, final int numberOfFallenPins) {
        if (!first.complete()) {
            first.bowl(numberOfFallenPins);
            return this;
        }
        validate(round, numberOfFallenPins);
        second = new Bowl(first.state()); // 2nd initiate
        second.bowl(numberOfFallenPins);
        return this;
    }

    private void validate(final int round, final int numberOfFallenPins) {
        if (endFrame()) {
            throw new EndedFrameException(round);
        }
        if (first.pins() + numberOfFallenPins > Bowl.MAX_NUMBER_OF_PIN) {
            throw new MaximumSumExceededException(first.pins(), numberOfFallenPins);
        }
    }

    public int first() {
        return first.pins();
    }

    public int second() {
        return second.pins();
    }

    public ThrowingState intermediateState() {
        return first.state();
    }

    public ThrowingState finalState() {
        return second.state();
    }

    public boolean endFrame() {
        return (first.pins() == Bowl.MAX_NUMBER_OF_PIN) || second.complete();
    }

    public boolean halfOfFrame() {
        return (first.pins() != Bowl.READY) && !second.complete();
    }
}
