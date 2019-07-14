package domain;

import static domain.Bowling.TOTAL_FRAME_COUNT;

public class FrameCounter {
    private static int frameCounter = 0;

    public FrameCounter(int frameCount) {
        this.frameCounter = frameCount;
    }

    public static boolean addFrameCount() {
        if (frameCounter == TOTAL_FRAME_COUNT) {
            return Boolean.FALSE;
        }
        frameCounter += 1;
        return Boolean.TRUE;
    }

    public static boolean isFinalFrame() {
        return frameCounter == TOTAL_FRAME_COUNT ? Boolean.TRUE : Boolean.FALSE;
    }

    public static int getFrameCounter() {
        return frameCounter;
    }
}
