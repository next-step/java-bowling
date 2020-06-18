package bowling.domain;

import java.util.Objects;

public class Frame {
    private final ThrowResults throwResults;
    private Frame nextFrame;

    public Frame(ThrowResults throwResults){
        this(throwResults, null);
    }

    Frame(ThrowResults throwResults, Frame nextFrame) {
        this.throwResults = throwResults;
        this.nextFrame = nextFrame;
    }

    public Frame next(int firstNumberHitPin, int secondNumberOfHitPin) {
        Frame nextFrame = new Frame(ThrowResults.of(firstNumberHitPin, secondNumberOfHitPin));
        this.nextFrame = nextFrame;
        return nextFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return Objects.equals(throwResults, frame.throwResults) &&
                Objects.equals(nextFrame, frame.nextFrame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(throwResults, nextFrame);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "throwResults=" + throwResults +
                ", nextFrame=" + nextFrame +
                '}';
    }
}
