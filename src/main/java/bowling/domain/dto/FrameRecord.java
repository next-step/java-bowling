package bowling.domain.dto;

import bowling.domain.Pin;
import bowling.domain.frame.Frame;
import bowling.domain.frame.FrameType;
import bowling.domain.state.StateType;

import java.util.List;

public class FrameRecord {

    private final FrameType kind;
    private final List<Integer> scores;
    private final Integer bonus;
    private final StateType state;
    private final Integer point;

    public FrameRecord(FrameType kind, List<Integer> scores, Integer bonus, StateType state, Integer point) {
        this.kind = kind;
        this.scores = scores;
        this.bonus = bonus;
        this.state = state;
        this.point = point;
    }

    public static FrameRecord of(Frame frame) {
        return new FrameRecord(FrameType.valueOf(frame)
                , frame.getState().getRecord()
                , frame.getBonus().map(Pin::getValue).orElse(null)
                , StateType.valueOf(frame.getState())
                , frame.calculatePoint());
    }

    public List<Integer> getScores() {
        return scores;
    }

    public Integer getBonus() {
        return bonus;
    }

    public StateType getState() {
        return state;
    }

    public FrameType getKind() {
        return kind;
    }

    public Integer getPoint() {
        return point;
    }
}
