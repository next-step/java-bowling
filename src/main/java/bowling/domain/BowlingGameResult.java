package bowling.domain;

import java.util.Objects;

public class BowlingGameResult {
    private final FrameResults frameResults;

    public BowlingGameResult(FrameResults frameResults) {
        this.frameResults = frameResults;
    }

    public FrameResults getFrameResults() {
        return this.frameResults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGameResult that = (BowlingGameResult) o;
        return Objects.equals(frameResults, that.frameResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResults);
    }

    @Override
    public String toString() {
        return "BowlingGameResult{" +
                "frameResults=" + frameResults +
                '}';
    }
}
