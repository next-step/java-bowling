package domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrameResults {

    private List<FrameResult> frameResults;

    private FrameResults(List<FrameResult> frameResults) {
        this.frameResults = new ArrayList<>(frameResults);
    }

    public static FrameResults from(List<FrameResult> frameResults) {
        return frameResults.stream()
                .reduce((previous, current) -> current.updateResult(previous))
                .map(frameResult -> new FrameResults(frameResults))
                .get();
    }

    public List<FrameResult> getFrameResults() {
        return Collections.unmodifiableList(frameResults);
    }
}
