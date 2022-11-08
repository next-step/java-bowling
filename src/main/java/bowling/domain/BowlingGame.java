package bowling.domain;

public class BowlingGame {
    private final Player player;
    private final Records records;

    public BowlingGame(Player player) {
        this.player = player;
        this.records = new Records();
    }

    public boolean isEndGame() {
        return records.isLastFrame() && records.isEndLastFrame();
    }

    public void doGame(int downPinCount) {
        records.record(downPinCount);
    }

    public int getFrameRound() {
        int recordCount = records.getRecordCount();
        if (recordCount == 0) {
            return 1;
        }

        if (records.isEndLastFrame()) {
            return recordCount + 1;
        }
        return recordCount;
    }

    public Records getRecords() {
        return records;
    }

    public Player getPlayer() {
        return player;
    }

}