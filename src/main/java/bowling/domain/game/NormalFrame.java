package bowling.domain.game;

import bowling.domain.pin.FramePins;
import bowling.domain.pin.FramePinsCreator;
import bowling.domain.pin.Pins;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int FIRST_FRAME_NUMBER = 1;

    private final FrameNumber frameNumber;
    private FramePins framePins;

    private NormalFrame(final FrameNumber frameNumber) {
        Objects.requireNonNull(frameNumber);
        this.frameNumber = frameNumber;
    }

    public static NormalFrame init() {
        return new NormalFrame(FrameNumber.of(FIRST_FRAME_NUMBER));
    }

    public static NormalFrame initBy(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public Frame next(int countOfPins) {
        this.framePins = FramePinsCreator.next(framePins, Pins.of(countOfPins));

        if (framePins.hasNext()) {
            return this;
        }

        return newFrame();
    }

    private Frame newFrame() {
        FrameNumber frameNumber = this.frameNumber.next();
        if (frameNumber.isEndNumber()) {
            return FinalFrame.init();
        }

        return new NormalFrame(frameNumber);
    }

    @Override
    public boolean hasNext() {
        return framePins.hasNext();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(framePins, that.framePins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, framePins);
    }
}
