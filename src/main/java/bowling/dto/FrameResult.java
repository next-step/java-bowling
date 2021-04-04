package bowling.dto;

import bowling.domain.PinCount;

import java.util.List;
import java.util.stream.Collectors;

public class FrameResult {

    private FrameScoreResult frameScoreResult;

    private List<Integer> pinCounts;

    public FrameResult(FrameScoreResult frameScoreResult, List<PinCount> pinCounts) {
        this.frameScoreResult = frameScoreResult;
        this.pinCounts = pinCounts.stream()
                .map(PinCount::count)
                .collect(Collectors.toList());
    }

    public FrameScoreResult frameScoreResult() {
        return frameScoreResult;
    }

    public List<Integer> pinCounts() {
        return pinCounts;
    }


}
