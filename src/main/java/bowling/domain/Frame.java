package bowling.domain;

import java.util.Objects;

public class Frame {
    private final ThrowResults throwResults;
    private final Frame nextFrame;

    public Frame(ThrowResults throwResults){
        this(throwResults, null);
    }

    Frame(ThrowResults throwResults, Frame nextFrame) {
        this.throwResults = throwResults;
        this.nextFrame = nextFrame;
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
}
