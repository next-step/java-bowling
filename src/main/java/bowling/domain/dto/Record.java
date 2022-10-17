package bowling.domain.dto;

import bowling.domain.frame.FrameType;
import bowling.domain.state.BowlingRecordState;

import java.util.List;

public class Record {

    private FrameType kind;
    private List<Integer> scores;
    private Integer bonus;
    private BowlingRecordState state;

    public Record(FrameType kind, List<Integer> scores, Integer bonus, BowlingRecordState state) {
        this.kind = kind;
        this.scores = scores;
        this.bonus = bonus;
        this.state = state;
    }

    public Record() {
    }

    public List<Integer> getScores() {
        return scores;
    }

    public Integer getBonus() {
        return bonus;
    }

    public BowlingRecordState getState() {
        return state;
    }

    public FrameType getKind() {
        return kind;
    }
}
