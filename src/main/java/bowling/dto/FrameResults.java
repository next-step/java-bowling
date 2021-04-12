package bowling.dto;

import bowling.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;

public class FrameResults {

    private List<FrameResult> frameResults = new ArrayList<>();

    public FrameResults(List<Frame> frames) {
        frames.forEach(frame -> frameResults.add(new FrameResult(frame)));
    }

    public List<FrameResult> results() {
        return frameResults;
    }

}
