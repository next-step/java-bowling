package bowling.domain;

public class TurnStore {
    private int currentFrameIndex;
    private int currentLaneIndex;

    public String getPlayerName(Records records) {
        return records.getPlayerName(currentLaneIndex);
    }

    public void record(Records records, int downPinCount) {
        records.record(currentLaneIndex, downPinCount);
        setNextTurn(records);
    }

    private void setNextTurn(Records records) {
        if (isEndGame(records)) {
            return;
        }

        if (isEndFrames(records)) {
            setNextFrame();
        }

        if (isEndPlayerFrame(records)) {
            setNextLane();
        }
    }

    private boolean isEndGame(Records records) {
        return records.isEndRecords();
    }

    private boolean isEndFrames(Records records) {
        return records.isEndFrames(currentFrameIndex);
    }

    private void setNextFrame() {
        currentLaneIndex = 0;
        currentFrameIndex++;
    }

    private boolean isEndPlayerFrame(Records records) {
        return records.isEndPlayerFrame(currentFrameIndex, currentLaneIndex);
    }

    private void setNextLane() {
        currentLaneIndex++;
    }

}
