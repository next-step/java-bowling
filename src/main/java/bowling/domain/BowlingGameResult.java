package bowling.domain;

import java.util.Objects;

public class BowlingGameResult {
    private final FrameResults frameResults;
    private final FrameScore frameScore;

    public BowlingGameResult(FrameResults frameResults, FrameScore frameScore) {
        this.frameResults = frameResults;
        this.frameScore = frameScore;
    }

    public BowlingGameResult(FrameResults frameResults) {
        this(frameResults, null);
    }

    public FrameResults getFrameResults() {
        return this.frameResults;
    }

    public FrameScore getFrameScore() {
        return frameScore;
    }

    public BowlingGameResult applyBonus(FrameScore frameScore) {
        return new BowlingGameResult(this.frameResults, frameScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGameResult that = (BowlingGameResult) o;
        return Objects.equals(frameResults, that.frameResults) &&
                Objects.equals(frameScore, that.frameScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResults, frameScore);
    }

    @Override
    public String toString() {
        return "BowlingGameResult{" +
                "frameResults=" + frameResults +
                ", frameScore=" + frameScore +
                '}';
    }
}
