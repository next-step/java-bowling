package bowling.domain;

import lombok.Getter;

public class Frame {
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
        return of(this.frameIndex + 1, clearPinCount);
    }

    public boolean isEndFrame() {
        return frameRounds.isEnd();
    }

    private static Frame of(int frameIndex, int clearPinCount) {
        return new Frame(frameIndex, clearPinCount);
    }

    private static Frame of(int clearPinCount) {
        return of(0, clearPinCount);
    }
}
