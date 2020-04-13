package bowling.domain;

import lombok.Getter;

public class Frame {
    public static final int MAX_FRAME_NUMBER = 10;
    public static final int MAX_FRAME_INDEX = MAX_FRAME_NUMBER - 1;
    private static final int ONE = 1;
    private static final int ZERO = 0;

    @Getter
    private int frameIndex;
    @Getter
    private FrameRounds frameRounds;

    private Frame(int frameIndex, int clearPinCount) {
        this.frameIndex = frameIndex;
        this.frameRounds = FrameRounds.of(clearPinCount);
    }

    public void roundPlay(int clearCount) {
        frameRounds.play(clearCount);
    }

    public static Frame fistFrame(int clearPinCount) {
        return of(clearPinCount);
    }

    public Frame next(int clearPinCount) {
        return of(this.frameIndex + ONE, clearPinCount);
    }

    public boolean isEndFrame() {
        return frameRounds.isEnd(isLastFrame());
    }

    private static Frame of(int frameIndex, int clearPinCount) {
        return new Frame(frameIndex, clearPinCount);
    }

    private static Frame of(int clearPinCount) {
        return of(ZERO, clearPinCount);
    }

    private boolean isLastFrame() {
        return frameIndex == MAX_FRAME_INDEX;
    }
}
