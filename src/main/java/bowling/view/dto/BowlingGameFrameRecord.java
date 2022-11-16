package bowling.view.dto;

import java.util.List;

public class BowlingGameFrameRecord {
    private final ScoreDto score;
    private final List<BowlRecord> bowlRecords;

    public BowlingGameFrameRecord(ScoreDto score, List<BowlRecord> bowlRecords) {
        this.score = score;
        this.bowlRecords = bowlRecords;
    }

    public List<BowlRecord> getBowlRecords() {
        return bowlRecords;
    }

    public ScoreDto getScore() {
        return score;
    }
}
