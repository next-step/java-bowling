package bowling.dto;

import bowling.domain.Frame;
import bowling.domain.FrameNumber;

import java.util.List;

public class NormalFrameResult {

    private int frameNumber;

    private FrameScoreResult frameScoreResult;

    private List<Integer> pinCounts;

    public NormalFrameResult(int frameNumber, FrameScoreResult frameScoreResult, List<Integer> pinCounts) {
        this.frameNumber = frameNumber;
        this.frameScoreResult = frameScoreResult;
        this.pinCounts = pinCounts;
    }

    public int frameNumber() {
        return frameNumber;
    }

}
