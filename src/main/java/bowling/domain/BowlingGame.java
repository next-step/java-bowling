package bowling.domain;

public class BowlingGame {
    private final Player player;
    private final Records records;

    public Records getRecords() {
        return records;
    }

    public Player getPlayer() {
        return player;
    }

    public BowlingGame(Player player) {
        this.player = player;
        this.records = new Records();
    }

    public boolean isEndGame() {
        if (records.getRecordCount() >= RuleConfig.NUMBER_OF_FRAME && records.isEndLastFrame()) {
            return true;
        }
        return false;
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
}