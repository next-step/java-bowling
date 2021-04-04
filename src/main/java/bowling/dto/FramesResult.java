package bowling.dto;

import bowling.domain.FinalFrame;
import bowling.domain.NormalFrame;

import java.util.List;
import java.util.stream.Collectors;

public class FramesResult {

    private List<NormalFrameResult> normalFrameResults;

    private FinalFrameResult finalFrameResult;

    public FramesResult(List<NormalFrame> normalFrames, FinalFrame finalFrame) {
        this.normalFrameResults = normalFrames.stream()
        .map(NormalFrame::result)
                .collect(Collectors.toList());
        this.finalFrameResult = finalFrame.result();
    }

    public List<NormalFrameResult> normalFrameResults() {
        return normalFrameResults;
    }

    public FinalFrameResult finalFrameResult() {
        return finalFrameResult;
    }

}
