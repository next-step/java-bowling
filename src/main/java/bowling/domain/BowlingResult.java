package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class BowlingResult {
    private final List<FrameResult> frameResults;

    public BowlingResult() {
        frameResults = new ArrayList<>();
    }

    public BowlingResult(List<FrameResult> frameResults) {
        this.frameResults = frameResults;
    }

    public List<FrameResult> results() {
        return frameResults;
    }

    public FrameResult result(FrameNumber frameNumber) {
        int frameResultsIndex = frameNumberToIndex(frameNumber);
        if (isOverFrameResultsIndex(frameResultsIndex)) {
            return new FrameResult();
        }

        return frameResults.get(frameResultsIndex);
    }

    private boolean isOverFrameResultsIndex(int frameResultsIndex) {
        return frameResultsIndex >= frameResults.size();
    }

    private int frameNumberToIndex(FrameNumber frameNumber) {
        return frameNumber.number() - 1;
    }
}
