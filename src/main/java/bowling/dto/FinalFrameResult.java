package bowling.dto;

import bowling.domain.Frame;
import bowling.domain.FrameNumber;

import java.util.List;
import java.util.stream.Collectors;

public class FinalFrameResult {

    private int frameNumber;

    private List<FrameResult> frameResults;

    public FinalFrameResult(FrameNumber frameNumber, List<Frame> frames) {
        this.frameNumber = frameNumber.number();
        this.frameResults = frames.stream()
                .map(Frame::result)
                .collect(Collectors.toList());
    }

    public int frameNumber() {
        return frameNumber;
    }

    public List<FrameResult> frameResults() {
        return frameResults;
    }
}
