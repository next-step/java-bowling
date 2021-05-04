package bowling.domain;

import java.util.Objects;

public class FrameResult {
    private final Score score;
    private final PointSymbols pointSymbols;

    public FrameResult() {
        this(new PointSymbols(), Score.createNotDetermined());
    }

    public FrameResult(Score score) {
        this(new PointSymbols(), score);
    }

    public FrameResult(PointSymbols pointSymbols) {
        this(pointSymbols, Score.createNotDetermined());
    }

    public FrameResult(PointSymbols pointSymbols, Score score) {
        this.score = score;
        this.pointSymbols = pointSymbols;
    }

    public Score score() {
        return score;
    }

    public PointSymbols pointSymbols() {
        return pointSymbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameResult that = (FrameResult) o;
        return Objects.equals(score, that.score) && Objects.equals(pointSymbols, that.pointSymbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, pointSymbols);
    }
}
