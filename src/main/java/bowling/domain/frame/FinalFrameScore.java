package bowling.domain.frame;

import bowling.domain.pin.FinalPins;

public final class FinalFrameScore extends FrameScore {

    public FinalFrameScore(FinalPins pins) {
        this(pins, pins.frameStatus());
    }

    private FinalFrameScore(FinalPins pins, FrameStatus frameStatus) {
        super(pins, frameStatus);
    }

    @Override
    public String status() {
        return null;
    }
}
