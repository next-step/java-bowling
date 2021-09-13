package bowling.domain.bowling;

import bowling.domain.Pin.Pins;

public class Frame {

    private static final int FIRST_FRAME_STAGE_VALUE = 1;
    private static final int NEXT_STAGE_PLUS_VALUE = 1;

    private final int frameStage;
    private final Pins pins;
    private Frame nextFrame;

    private Frame(int frameStage, Pins pins, Frame nextFrame) {
        this.frameStage = frameStage;
        this.pins = pins;
        this.nextFrame = nextFrame;
    }

    public static Frame ofStart(Pins pins) {
        return new Frame(FIRST_FRAME_STAGE_VALUE, pins, null);
    }

    public Frame ofNext(Pins pins) {
        return this.nextFrame = new Frame(frameStage + NEXT_STAGE_PLUS_VALUE, pins, null);
    }

    public int frameStage() {
        return frameStage;
    }

}
