package bowling.domain;

import java.util.List;

public class BowlingGameFrameRecord {
    private final List<BowlRecord> bowlRecords;

    public BowlingGameFrameRecord(List<BowlRecord> bowlRecords) {
        this.bowlRecords = bowlRecords;
    }

    public List<BowlRecord> getBowlRecords() {
        return bowlRecords;
    }
}
