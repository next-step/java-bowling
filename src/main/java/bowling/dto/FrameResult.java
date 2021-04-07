package bowling.dto;

import bowling.domain.FrameNumber;
import bowling.domain.FrameScoreResult;

import java.util.List;

public class FrameResult {

    private int frameNumber;

    private FrameScoreResult frameScoreResult;

    private List<Integer> pinCounts;

    public FrameResult(FrameNumber frameNumber,List<Integer> pinCounts) {
        this.frameNumber = frameNumber.number();
        Integer sum = pinCounts.stream()
                .reduce(0, Integer::sum);
        this.frameScoreResult = FrameScoreResult.of(sum,pinCounts.size());
        this.pinCounts = pinCounts;
    }

    public FrameScoreResult frameScoreResult() {
        return frameScoreResult;
    }

    public List<Integer> pinCounts() {
        return pinCounts;
    }


}
