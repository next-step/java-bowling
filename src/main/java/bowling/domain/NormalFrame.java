package bowling.domain;

import bowling.domain.state.ThrowingState;
import bowling.exception.EndedFrameException;
import bowling.exception.MaximumSumExceededException;

public class NormalFrame implements Frame {
    private Bowl first;
    private Bowl second;

    public NormalFrame() {
        this.first = new Bowl(this);
        this.second = new Bowl(this);
    }

    @Override
    public NormalFrame play(final int round, final int numberOfFallenPins) {
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

    @Override
    public int first() {
        return first.pins();
    }

    @Override
    public int second() {
        return second.pins();
    }

    @Override
    public ThrowingState firstState() {
        return first.state();
    }

    @Override
    public ThrowingState secondState() {
        return second.state();
    }

    @Override
    public boolean endFrame() {
        return (first.pins() == Bowl.MAX_NUMBER_OF_PIN) || second.complete();
    }

    @Override
    public boolean firstOfFrame() {
        return (first.pins() != Bowl.READY) && !second.complete();
    }
}
