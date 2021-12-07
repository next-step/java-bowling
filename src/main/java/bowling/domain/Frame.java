package bowling.domain;

import java.util.Objects;

import static bowling.domain.Sequential.*;

public class Frame {
    private final Sequential previousSequential;
    private final PinNumbers pinNumbers;

    public Frame(Sequential sequential) {
        this(sequential, new PinNumbers());
    }

    public Frame(Sequential sequential, PinNumbers pinNumbers) {
        this.previousSequential = sequential;
        this.pinNumbers = pinNumbers;
    }

    public PinNumbers getPinNumbers() {
        return pinNumbers;
    }

    public boolean continuable() {
        return pinNumbers.isContinuable();
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
            return DOUBLE_STRIKE;
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
        return previousSequential.equals(Sequential.STRIKE) || previousSequential.equals(DOUBLE_STRIKE);
    }

    public Score score(Frame first, Frame second) {
        Score res = pinNumbers.totalScore();

        if (currentSequential().equals(DOUBLE_STRIKE)) {
            return res.add(first.pinNumbers.totalScore())
                    .add(second.pinNumbers.firstScore());
        }

        if (currentSequential().equals(Sequential.STRIKE)) {
            return res.add(first.pinNumbers.totalScore());
        }

        if (currentSequential().equals(Sequential.SPARE)) {
            return res.add(first.pinNumbers.firstScore());
        }

        return res;
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

        return previousSequential == frame.previousSequential && Objects.equals(pinNumbers, frame.pinNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousSequential, pinNumbers);
    }
}
