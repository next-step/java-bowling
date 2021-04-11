package bowling.dto;

import bowling.domain.frame.*;

import java.util.ArrayList;
import java.util.List;

public class FrameResults {

    private List<FrameResult2> frameResults = new ArrayList<>();

    public FrameResults(List<Frame> frames) {
        frames.forEach(frame -> {
            frameResults.add(new FrameResult2(frame));
        });
    }

    public List<FrameResult2> results() {
        return frameResults;
    }

    public List<NormalFrameResult> normalFrameResults() {
        return null;
    }

    public FinalFrameResult finalFrameResult() {
        return null;
    }

}
