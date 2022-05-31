package bowling.domain;

import bowling.domain.state.last.LastReady;
import bowling.domain.state.ThrowingState;
import bowling.domain.state.last.SecondReady;
import bowling.exception.EndedFrameException;
import bowling.exception.MaximumSumExceededException;

public class LastFrame implements Frame {
    private Bowl first;
    private Bowl second;
    private Bowl third;

    public LastFrame() {
        this.first = new Bowl(this);
        this.second = new Bowl(this);
        this.third = new Bowl(new LastReady(this));
    }

    @Override
    public LastFrame play(int round, int numberOfFallenPins) {
        if (!first.complete()) {
            first.bowl(numberOfFallenPins);
            return this;
        }
        if (!second.complete()) {
            validate(numberOfFallenPins);
            // 2nd initiate
            if (first() != Bowl.MAX_NUMBER_OF_PIN) {
                second = new Bowl(first.state());
            } else {
                second = new Bowl(new SecondReady(this));
            }
            second.bowl(numberOfFallenPins);
            return this;
        }
        validate(round, numberOfFallenPins);
        if (first() == Bowl.MAX_NUMBER_OF_PIN && second() != Bowl.MAX_NUMBER_OF_PIN) {
            third = new Bowl(second.state()); // 3rd initiate
        }
        third.bowl(numberOfFallenPins);
        return this;
    }

    private void validate(final int numberOfFallenPins) {
        if (first.pins() != Bowl.MAX_NUMBER_OF_PIN && first.pins() + numberOfFallenPins > Bowl.MAX_NUMBER_OF_PIN) {
            throw new MaximumSumExceededException(first.pins(), numberOfFallenPins);
        }
    }

    private void validate(final int round, final int numberOfFallenPins) {
        if (endFrame()) {
            throw new EndedFrameException(round);
        }
        if (first.pins() == Bowl.MAX_NUMBER_OF_PIN && second.pins() != Bowl.MAX_NUMBER_OF_PIN && second.pins() + numberOfFallenPins > Bowl.MAX_NUMBER_OF_PIN) {
            throw new MaximumSumExceededException(second.pins(), numberOfFallenPins);
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

    public int third() {
        return third.pins();
    }

    @Override
    public boolean endFrame() {
        return (second.complete() && (first.pins() + second.pins() < Bowl.MAX_NUMBER_OF_PIN)) || third.complete();
    }

    @Override
    public boolean firstOfFrame() {
        return (first.pins() != Bowl.READY) && !second.complete();
    }

    public boolean secondOfFrame() {
        return (second.pins() != Bowl.READY) && !third.complete();
    }

    @Override
    public ThrowingState firstState() {
        return first.state();
    }

    @Override
    public ThrowingState secondState() {
        return second.state();
    }

    public ThrowingState thirdState() {
        return third.state();
    }
}
