package bowling.dto;

import bowling.domain.State.StateType;
import bowling.domain.frame.PinCounts;

import java.util.List;

public class PinCountResult {

    private StateType stateType;

    private List<Integer> pinCounts;

    public PinCountResult(PinCounts pinCounts) {
        this.stateType = pinCounts.currentState();
        this.pinCounts = pinCounts.pinCountsAsInt();
    }

    public StateType stateType() {
        return stateType;
    }

    public List<Integer> pinCounts() {
        return pinCounts;
    }
}
