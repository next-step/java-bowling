package bowling.dto;

import bowling.domain.frame.FrameOld;
import bowling.domain.frame.FrameNumber;

public class NormalFrameResult {

    private int frameNumber;

    private FrameResult frameResult;

    public NormalFrameResult(FrameNumber frameNumber, FrameOld frame) {
        this.frameNumber = frameNumber.number();
        this.frameResult = frame.result();
    }

    public int frameNumber() {
        return frameNumber;
    }

    public FrameResult frameResult() {
        return frameResult;
    }


}
