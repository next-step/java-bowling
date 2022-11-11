package bowling.domain;

import java.util.List;

public class BowlingGame {
    private final Records records;
    private final Sequence currentSequence;

    public BowlingGame(List<String> names) {
        Players players = new Players(names);
        this.records = players.getRecords();
        this.currentSequence = new Sequence();
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

    public void doGame(int downPinCount) {
        records.record(currentSequence.getCurrentLaneIndex(), downPinCount);
        setNextTurn();
    }

    public void doGame(int testNumber, int downPinCount) {
        records.record(currentSequence.getCurrentLaneIndex(), downPinCount);
        setNextTurn();
    }

    private void setNextTurn() {
        if (isEndGame()) {
            return;
        }

        if (isEndFrames(currentSequence.getCurrentFrameIndex())) {
            currentSequence.setNextFrame();
        }

        if (isEndPlayerFrame(currentSequence.getCurrentFrameIndex(), currentSequence.getCurrentLaneIndex())) {
            currentSequence.setNextLane();
        }
    }

    public String getPlayerName(int laneIndex) {
        return records.getPlayerName(laneIndex);
    }

}