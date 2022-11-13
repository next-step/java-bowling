package bowling.dto;

import bowling.domain.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.state.StateType;

import java.util.List;
import java.util.stream.Collectors;

public class FrameRecord {

    private final List<Integer> scores;
    private final List<Integer> bonus;
    private final StateType state;
    private final Integer point;

    public FrameRecord(List<Integer> scores, List<Integer> bonus, StateType state, Integer point) {
        this.scores = scores;
        this.bonus = bonus;
        this.state = state;
        this.point = point;
    }

    public static FrameRecord of(Frame frame) {
        return new FrameRecord(frame.getState().getRecord()
                , frame.getBonus().stream().map(Pin::getValue).collect(Collectors.toUnmodifiableList())
                , StateType.valueOf(frame.getState())
                , frame.calculatePoint());
    }

    public List<Integer> getScores() {
        return scores;
    }

    public List<Integer> getBonus() {
        return bonus;
    }

    public StateType getState() {
        return state;
    }

    public Integer getPoint() {
        return point;
    }
}
