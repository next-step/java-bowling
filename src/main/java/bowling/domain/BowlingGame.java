package bowling.domain;

import java.util.List;

public class BowlingGame {
    private final Records records;
    private final Sequence currentSequence;

    public BowlingGame(List<String> names) {
        this.records = new Records(names);
        this.currentSequence = new Sequence();
    }

    public List<Record> getRecords() {
        return records.getRecords();
    }

    public boolean isEndGame() {
        return records.isEndRecords();
    }

    public void doGame(int downPinCount) {
        currentSequence.record(records, downPinCount);
    }

    public String getPlayerName() {
        return currentSequence.getPlayerName(records);
    }

}