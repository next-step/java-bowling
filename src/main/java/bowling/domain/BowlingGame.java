package bowling.domain;

import java.util.List;

public class BowlingGame {
    private final Records records;

    public BowlingGame(List<String> names) {
        Players players = new Players(names);
        this.records = players.getRecords();
    }

    public List<Record> getRecords() {
        return records.getRecords();
    }

    public boolean isEndFrames(int frameIndex) {
        return records.isEndFrames(frameIndex);
    }

    public boolean isEndPlayerFrame(int frameIndex, int playerIndex) {
        return records.isEndPlayerFrame(frameIndex, playerIndex);
    }

    public boolean isEndGame() {
        return records.isEndRecords();
    }

    public void doGame(int laneIndex, int downPinCount) {
        records.record(laneIndex, downPinCount);
    }

    public String getPlayerName(int laneIndex) {
        return records.getPlayerName(laneIndex);
    }

}