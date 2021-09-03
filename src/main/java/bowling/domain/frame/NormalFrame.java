package bowling.domain.frame;

import java.util.Objects;

public class NormalFrame {

    private final int first;
    private final int second;

    public NormalFrame(int first, int second) {
        if (first > 10 || second > 10) {
            throw new NormalFrameException();
        }
        if (first < 0 || second < 0) {
            throw new NormalFrameException();
        }
        if (first == 10 && second > 0) {
            throw new NormalFrameException();
        }
        if (first + second > 10) {
            throw new NormalFrameException();
        }
        this.first = first;
        this.second = second;
    }

    public NormalFrame(int first) {
        this(first, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return first == that.first && second == that.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
