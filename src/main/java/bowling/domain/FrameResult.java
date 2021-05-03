package bowling.domain;

import java.util.Objects;

public class FrameResult {
    private final Score aggregatedScore;
    private final PointSymbols pointSymbols;

    public FrameResult() {
        this(Score.createNotDetermined(), new PointSymbols());
    }

    public FrameResult(Score aggregatedScore, PointSymbols pointSymbols) {
        this.aggregatedScore = aggregatedScore;
        this.pointSymbols = pointSymbols;
    }

    public FrameResult(PointSymbols pointSymbols, Score aggregatedScore) {
        this.aggregatedScore = aggregatedScore;
        this.pointSymbols = pointSymbols;
    }

    public Score aggregatedScore() {
        return aggregatedScore;
    }

    public PointSymbols pointSymbols() {
        return pointSymbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameResult that = (FrameResult) o;
        return Objects.equals(aggregatedScore, that.aggregatedScore) && Objects.equals(pointSymbols, that.pointSymbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregatedScore, pointSymbols);
    }
}
