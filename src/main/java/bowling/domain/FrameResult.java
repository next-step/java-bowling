package bowling.domain;

import java.util.Objects;

public class FrameResult {
    private final PointSymbols pointSymbols;
    private final Score score;

    public FrameResult() {
        this(new PointSymbols());
    }

    public FrameResult(PointSymbols pointSymbols) {
        this(pointSymbols, Score.createNotDetermined());
    }

    public FrameResult(PointSymbols pointSymbols, Score score) {
        this.pointSymbols = pointSymbols;
        this.score = score;
    }

    public PointSymbols pointSymbols() {
        return pointSymbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameResult that = (FrameResult) o;
        return Objects.equals(pointSymbols, that.pointSymbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointSymbols);
    }

    public Score score() {
        return score;
    }
}
