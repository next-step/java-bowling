package bowling.domain;

public class Sequence {
    int currentFrameIndex;
    int currentLaneIndex;

    public int getCurrentFrameIndex() {
        return currentFrameIndex;
    }

    public int getCurrentLaneIndex() {
        return currentLaneIndex;
    }

    public void setNextLane() {
        currentLaneIndex++;
    }

    public void setNextFrame() {
        currentLaneIndex = 0;
        currentFrameIndex++;
    }
}
