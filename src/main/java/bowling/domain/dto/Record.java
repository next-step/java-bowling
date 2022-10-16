package bowling.domain.dto;

import bowling.domain.frame.KindOfFrame;
import bowling.domain.state.BowlingRecordState;

import java.util.List;

public class Record {

    private KindOfFrame kind;
    private List<Integer> scores;
    private Integer bonus;
    private BowlingRecordState state;

    public Record(KindOfFrame kind, List<Integer> scores, Integer bonus, BowlingRecordState state) {
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

    public KindOfFrame getKind() {
        return kind;
    }
}
