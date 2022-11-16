package bowling.view.dto;

import java.util.List;

import bowling.domain.frame.Score;

public class BowlingGameFrameRecord {
    private final Score score;
    private final List<BowlRecord> bowlRecords;

    public BowlingGameFrameRecord(Score score, List<BowlRecord> bowlRecords) {
        this.score = score;
        this.bowlRecords = bowlRecords;
    }

    public List<BowlRecord> getBowlRecords() {
        return bowlRecords;
    }

    public Score getScore() {
        return score;
    }
}
