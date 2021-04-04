package bowling.dto;

import bowling.domain.Frame;
import bowling.domain.FrameNumber;

public class NormalFrameResult {

    private int frameNumber;

    private FrameResult frameResult;

    public NormalFrameResult(FrameNumber frameNumber, Frame frame) {
        this.frameNumber = frameNumber.number();
        this.frameResult = frame.result();
    }

    public int frameNumber() {
        return frameNumber;
    }

    public FrameResult framesResult() {
        return frameResult;
    }
}
