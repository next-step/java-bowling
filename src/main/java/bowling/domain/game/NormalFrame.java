package bowling.domain.game;

import bowling.domain.pin.FramePins;
import bowling.domain.pin.FramePinsCreator;
import bowling.domain.pin.Pins;

import java.util.Objects;

public class NormalFrame implements Frame {
    private static final int FIRST_FRAME_NUMBER = 1;

    private final FrameNumber frameNumber;
    private FramePins framePins;

    public NormalFrame(final FrameNumber frameNumber) {
        Objects.requireNonNull(frameNumber);
        this.frameNumber = frameNumber;
    }

    public NormalFrame(final FrameNumber frameNumber, FramePins framePins) {
        Objects.requireNonNull(frameNumber);
        this.frameNumber = frameNumber;
        this.framePins = framePins;
    }

    public static NormalFrame init() {
        return new NormalFrame(FrameNumber.of(FIRST_FRAME_NUMBER));
    }

    public static NormalFrame initBy(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public Frame next(int countOfPins) {
        FramePins framePins = FramePinsCreator.next(this.framePins, Pins.of(countOfPins));

        if (this.framePins != null && this.framePins.isEnd()) {
            return newFrame(framePins);
        }

        this.framePins = framePins;
        return this;
    }

    private Frame newFrame(FramePins framePins) {
        FrameNumber frameNumber = this.frameNumber.next();
        if (frameNumber.isEndNumber()) {
            return FinalFrame.init(framePins);
        }

        return new NormalFrame(frameNumber, framePins);
    }

    @Override
    public boolean canBowling() {
        if (framePins == null) {
            return true;
        }

        return !framePins.isEnd();
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

    @Override
    public String toString() {
        return (framePins != null) ? framePins.toString() : "   ";
    }
}
