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
        if (records.getRecordCount() != RuleConfig.NUMBER_OF_FRAME) {
            return false;
        }
        return records.isEndLastFrame();
    }

    public void doGame(int downPinCount) {
        records.record(downPinCount);
    }

}