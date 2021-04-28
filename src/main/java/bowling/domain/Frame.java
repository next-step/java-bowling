package bowling.domain;

import static bowling.controller.BowlingGameController.MAX_FRAME_NO;

public class Frame {
    private final FrameType frameType;

    public Frame(int frameNo) {
        this.frameType = FrameFactory.typeFactory(frameNo, MAX_FRAME_NO);
    }

    public Frame pitch(int point) {
        frameType.pitch(point);
        return this;
    }

    public boolean isContinue() {
        return frameType.isContinue();
    }

    public Pitches pitches() {
        return frameType.pitches();
    }
}
