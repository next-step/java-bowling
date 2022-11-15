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
        if (records.isEndRecords()) {
            return;
        }

        if (records.isEndFrames(currentFrameIndex)) {
            setNextFrame();
        }

        if (records.isEndPlayerFrame(currentFrameIndex, currentLaneIndex)) {
            setNextLane();
        }
    }

    private void setNextFrame() {
        currentLaneIndex = 0;
        currentFrameIndex++;
    }

    private void setNextLane() {
        currentLaneIndex++;
    }

}
