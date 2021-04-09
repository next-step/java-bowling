package bowling.dto;

import bowling.domain.State.StateType;
import bowling.domain.frame.PinCounts;

import java.util.List;

public class PinCountsResult {

    private StateType stateType;

    private List<Integer> pinCounts;

    public PinCountsResult(StateType stateType, List<Integer> pinCounts) {
        this.stateType = stateType;
        this.pinCounts = pinCounts;
    }

    public StateType stateType() {
        return stateType;
    }

    public List<Integer> pinCounts() {
        return pinCounts;
    }
}
