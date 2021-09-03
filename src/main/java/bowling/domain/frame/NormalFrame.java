package bowling.domain.frame;

import java.util.Objects;

public class NormalFrame {

    private final int first;
    private final int second;

    public NormalFrame(int first, int second) {
        validatePitching(first, second);
        this.first = first;
        this.second = second;
    }

    public NormalFrame(int first) {
        this(first, 0);
    }

    private void validatePitching(int first, int second) {
        if (PitchingValidation.of(first, second) != PitchingValidation.NONE) {
            throw new NormalFrameException();
        }
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
