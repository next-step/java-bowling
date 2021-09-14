package bowling.domain;

public class FrameIndex {
    private static final int BEFORE_FINAL_FRAME_INDEX = 9;
    private final int frameIndex;

    public FrameIndex(int frameIndex) {
        validateFrameIndex(frameIndex);
        this.frameIndex = frameIndex;
    }

    private void validateFrameIndex(int frameIndex) {
        if (frameIndex < Frame.EMPTY_FRAME || frameIndex > Frame.LAST_FRAME) {
            throw new IllegalArgumentException("");
        }
    }

    public int getValue() {
        return frameIndex;
    }

    public boolean isBeforeFinalFrame() {
        return frameIndex == BEFORE_FINAL_FRAME_INDEX;
    }

    public boolean isNotStartFrame() {
        return frameIndex > 0;
    }
}
