package bowling.domain;

import java.util.Objects;

public class Frame {
    private final Sequential prevSequential;
    private final PinNumbers pinNumbers;

    public Frame(Sequential sequential) {
        this(sequential, new PinNumbers());
    }

    public Frame(Sequential sequential, PinNumbers pinNumbers) {
        this.prevSequential = sequential;
        this.pinNumbers = pinNumbers;
    }

    public Frame pitch(int number) {
        pinNumbers.addPinNumber(new PinNumber(number));

        if (pinNumbers.isContinuable()) {
            return this;
        }

        return new Frame(currentSequential());
    }

    private Sequential currentSequential() {
        if (isPrevStrike() && pinNumbers.isStrike()) {
            return Sequential.DOUBLE_STRIKE;
        }

        if (pinNumbers.isStrike()) {
            return Sequential.STRIKE;
        }

        if (pinNumbers.isSpare()) {
            return Sequential.SPARE;
        }

        return Sequential.NONE;
    }

    private boolean isPrevStrike() {
        return prevSequential.equals(Sequential.STRIKE) || prevSequential.equals(Sequential.DOUBLE_STRIKE);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Frame frame = (Frame) object;

        return prevSequential == frame.prevSequential && Objects.equals(pinNumbers, frame.pinNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prevSequential, pinNumbers);
    }
}
