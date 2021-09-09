package bowling.domain.frame;

import bowling.domain.Status;
import bowling.domain.frame.info.FrameInfo;
import bowling.domain.frame.info.NormalFrameInfo;
import bowling.domain.pins.Pins;

public class NormalFrame implements Frame {

    private final NormalFrameInfo frameInfo;
    private final Pins pins;

    private NormalFrame(NormalFrameInfo normalFrameInfo, Pins pins) {
        this.frameInfo = normalFrameInfo;
        this.pins = pins;
    }

    public static NormalFrame of(NormalFrameInfo normalFrameInfo, Pins pins) {
        return new NormalFrame(normalFrameInfo, pins);
    }

    public static NormalFrame create() {
        return of(NormalFrameInfo.create(), Pins.create());
    }

    @Override
    public Frame roll(int downPins) {
        Pins roll = pins.roll(downPins);

        if (isLastFrame()) {
            return FinalFrame.create();
        }

        if (hasNextFrame()) {
            return of(frameInfo.nextFrame(), Pins.create());
        }

        return of(frameInfo.nextRound(), roll);
    }

    private boolean isLastFrame() {
        return frameInfo.isEndFrame() && (frameInfo.isLastRound() || pins.isAllDown());
    }

    private boolean hasNextFrame() {
        return frameInfo.isLastRound() || pins.isAllDown();
    }

    @Override
    public int numberOfDownedPins() {
        return pins.numberOfPinDowns();
    }

    @Override
    public FrameInfo frameInfo() {
        return frameInfo;
    }

    @Override
    public boolean hasNextRound() {
        return !frameInfo.isLastRound() || pins.isAllDown();
    }

    @Override
    public Status pinStatus() {
        return pins.status();
    }

}
