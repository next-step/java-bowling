package bowling;

import bowling.entity.Score;

import java.util.Objects;

public class NormalFrame {
    private final Score score;

    public NormalFrame(Score score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
