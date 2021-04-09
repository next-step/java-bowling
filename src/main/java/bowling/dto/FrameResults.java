package bowling.dto;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class FrameResults {

    private List<NormalFrameResult> normalFrameResults = new ArrayList<>();

    private FinalFrameResult finalFrameResult;

    public FrameResults(List<Frame> frames) {
        frames.forEach(frame -> {
            if (frame instanceof NormalFrame) {
                normalFrameResults.add(((NormalFrame)frame).result());
            }else{
                finalFrameResult = ((FinalFrame)frame).result();
            }
        });
    }

    public List<NormalFrameResult> normalFrameResults() {
        return normalFrameResults;
    }

    public FinalFrameResult finalFrameResult() {
        return finalFrameResult;
    }

}
