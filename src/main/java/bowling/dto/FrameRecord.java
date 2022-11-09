package bowling.dto;

import bowling.domain.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameType;
import bowling.domain.state.StateType;

import java.util.List;
import java.util.stream.Collectors;

public class FrameRecord {

    private final FrameType kind;
    private final List<Integer> scores;
    private final List<Integer> bonus;
    private final StateType state;
    private final Integer point;

    public FrameRecord(FrameType kind, List<Integer> scores, List<Integer> bonus, StateType state, Integer point) {
        this.kind = kind;
        this.scores = scores;
        this.bonus = bonus;
        this.state = state;
        this.point = point;
    }

    public static FrameRecord of(Frame frame) {
        return new FrameRecord(FrameType.valueOf(frame)
                , frame.getState().getRecord()
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
