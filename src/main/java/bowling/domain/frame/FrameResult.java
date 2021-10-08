package bowling.domain.frame;

import java.util.Objects;

public class FrameResult {

    private final int score;

    private FrameResult(int score) {
        this.score = score;
    }

    public static FrameResult of(int score) {
        return new FrameResult(score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResult that = (FrameResult) o;
        return score == that.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

}
