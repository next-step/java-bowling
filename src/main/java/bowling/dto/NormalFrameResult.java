package bowling.dto;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.PinCounts;

import java.util.List;

public class NormalFrameResult implements FrameResult {

    private int frameNumber;

    private PinCountResult pinCountResult;

    public NormalFrameResult(FrameNumber frameNumber, PinCounts pinCounts) {
        this.frameNumber = frameNumber.number();
        this.pinCountResult = new PinCountResult(pinCounts);
    }

    public int frameNumber() {
        return frameNumber;
    }

    @Override
    public List<Integer> pinCounts() {
        return pinCountResult.pinCounts();
    }

    public PinCountResult frameResult() {
        return pinCountResult;
    }
}
