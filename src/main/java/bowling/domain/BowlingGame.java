package bowling.domain;

public class BowlingGame {
    private final Player player;
    private final Record record;

    public BowlingGame(Player player) {
        this.player = player;
        this.record = new Record();
    }

    public boolean isEndGame() {
        return record.isLastFrame() && record.isEndLastFrame();
    }

    public void doGame(int downPinCount) {
        record.record(downPinCount);
    }

    public int getFrameRound() {
        int recordCount = record.getRecordCount();
        if (recordCount == 0) {
            return 1;
        }

        if (record.isEndLastFrame()) {
            return recordCount + 1;
        }
        return recordCount;
    }

    public Record getRecord() {
        return record;
    }

    public Player getPlayer() {
        return player;
    }

}