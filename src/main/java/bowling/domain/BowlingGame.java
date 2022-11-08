package bowling.domain;

public class BowlingGame {
    private final Records records;

    public BowlingGame(Players players) {
        this.records = players.getRecords();
    }

    public boolean isEndGame() {
        return records.isEndRecords();
    }

    public boolean isEndLastFrame(int laneIndex) {
        return records.isEndLastFrame(laneIndex);
    }

    public void doGame(int laneIndex, int downPinCount) {
        records.record(laneIndex, downPinCount);
    }

    public int getFrameRound(int laneIndex) {
        int recordCount = records.getRecordCount(laneIndex);
        if (recordCount == 0) {
            return 1;
        }

        if (records.isEndLastFrame(laneIndex)) {
            return recordCount + 1;
        }
        return recordCount;
    }

    public Record getRecord(int laneIndex) {
        return records.getRecord(laneIndex);
    }

}