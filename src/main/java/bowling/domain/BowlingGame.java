package bowling.domain;

import java.util.List;

public class BowlingGame {
    private final Records records;
    private final TurnStore turnStore;

    public BowlingGame(List<String> names) {
        this.records = new Records(names);
        this.turnStore = new TurnStore();
    }

    public List<Record> getRecords() {
        return records.getRecords();
    }

    public boolean isEndGame() {
        return records.isEndRecords();
    }

    public void doGame(int downPinCount) {
        turnStore.record(records, downPinCount);
    }

    public String getPlayerName() {
        return turnStore.getPlayerName(records);
    }

}