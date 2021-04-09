package bowling.dto;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.PinCounts;

import java.util.List;

public class NormalFrameResult implements FrameResult {

    private int frameNumber;

    private PinCountsResult pinCountsResult;

    public NormalFrameResult(FrameNumber frameNumber, PinCounts pinCounts) {
        this.frameNumber = frameNumber.number();
        this.pinCountsResult = pinCounts.result();
    }

    public int frameNumber() {
        return frameNumber;
    }

    @Override
    public List<Integer> pinCounts() {
        return pinCountsResult.pinCounts();
    }

    public PinCountsResult pinCountsResult() {
        return pinCountsResult;
    }
}
