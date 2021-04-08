package bowling.dto;

import bowling.domain.frame.FrameNumber;
import bowling.domain.State.StateType;

import java.util.List;

public class FrameResult {

    private int frameNumber;

    private StateType stateType;

    private List<Integer> pinCounts;

    public FrameResult(FrameNumber frameNumber,List<Integer> pinCounts) {
        this.frameNumber = frameNumber.number();
        Integer sum = pinCounts.stream()
                .reduce(0, Integer::sum);
        this.frameScoreResult = StateType.of(sum,pinCounts.size());
        this.pinCounts = pinCounts;
    }

    public StateType frameScoreResult() {
        return frameScoreResult;
    }

    public List<Integer> pinCounts() {
        return pinCounts;
    }


}
