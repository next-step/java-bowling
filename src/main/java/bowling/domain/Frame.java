package bowling.domain;

import java.util.Objects;

public class Frame {

    public static final int PLUS_NUMBER = 1;
    private final int countOfFrame;

    public Frame(int countOfFrame) {
        this.countOfFrame = countOfFrame;
    }

    public Frame nextFrame() {
        return new Frame(this.countOfFrame + PLUS_NUMBER);
    }

    public int getCountOfFrame() {
        return countOfFrame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frame frame = (Frame) o;
        return countOfFrame == frame.countOfFrame;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfFrame);
    }

    @Override
    public String toString() {
        return "Frame{" +
                "countOfFrame=" + countOfFrame +
                '}';
    }

}
