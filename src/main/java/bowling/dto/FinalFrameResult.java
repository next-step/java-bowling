package bowling.dto;

import bowling.domain.frame.FrameOld;
import bowling.domain.frame.FrameNumber;

import java.util.List;
import java.util.stream.Collectors;

public class FinalFrameResult {

    private int frameNumber;

    private List<FrameResult> frameResults;

    public FinalFrameResult(FrameNumber frameNumber, List<FrameOld> frames) {
        this.frameNumber = frameNumber.number();
        this.frameResults = frames.stream()
                .map(FrameOld::result)
                .collect(Collectors.toList());
    }

    public int frameNumber() {
        return frameNumber;
    }

    public List<FrameResult> frameResults() {
        return frameResults;
    }
}
