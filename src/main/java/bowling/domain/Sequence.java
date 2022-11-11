package bowling.domain;

public class Sequence {
    int currentFrameIndex;
    int currentLaneIndex;

    public void setNextLane() {
        currentLaneIndex++;
    }

    public void setNextFrame() {
        currentLaneIndex = 0;
        currentFrameIndex++;
    }

    public boolean isEndFrames(Records records) {
        return records.isEndFrames(currentFrameIndex);
    }

    public boolean isEndPlayerFrame(Records records) {
        return records.isEndPlayerFrame(currentFrameIndex, currentLaneIndex);
    }

    public boolean isEndGame(Records records) {
        return records.isEndRecords();
    }

    public void record(Records records, int downPinCount) {
        records.record(currentLaneIndex, downPinCount);
        setNextTurn(records);
    }

    public String getPlayerName(Records records) {
        return records.getPlayerName(currentLaneIndex);
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
}
