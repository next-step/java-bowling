package bowling.domain;

import java.util.Objects;

public class SingleFrameResult {
    private final PointSymbols pointSymbols;
    private final Score score;
    private final Score aggregatedScore;

    public SingleFrameResult() {
        this(new PointSymbols());
    }

    public SingleFrameResult(PointSymbols pointSymbols) {
        this(pointSymbols, Score.createNotDetermined());
    }

    public SingleFrameResult(PointSymbols pointSymbols, Score score) {
        this(pointSymbols, score, Score.createNotDetermined());
    }

    public SingleFrameResult(Score score) {
        this(new PointSymbols(), score);
    }

    public SingleFrameResult(PointSymbols pointSymbols, Score score, Score aggregatedScore) {
        this.pointSymbols = pointSymbols;
        this.score = score;
        this.aggregatedScore = aggregatedScore;
    }

    public PointSymbols pointSymbols() {
        return pointSymbols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleFrameResult that = (SingleFrameResult) o;
        return Objects.equals(pointSymbols, that.pointSymbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointSymbols);
    }

    public Score score() {
        return score;
    }

    public Score aggregatedScore() {
        return aggregatedScore;
    }
}
