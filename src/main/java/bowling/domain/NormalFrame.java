package bowling.domain;

import bowling.domain.pitching.Pitching;
import bowling.domain.pitching.StandbyPitching;

import java.util.Objects;

public class NormalFrame implements Frame {

    private FrameNumber frameNumber;
    private Pitching pitching;
    private Frame nextFrame;

    private NormalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.pitching = new StandbyPitching();
    }

    public static NormalFrame of(FrameNumber frameNumber) {
        return new NormalFrame(frameNumber);
    }

    @Override
    public Frame figureOutFrame(FallenPinNumber fallenPinNumber) {
        Pitching action = pitching.pitch(fallenPinNumber);

        if (action.isFinished()) {
            nextFrame = generateNextFrame();
            return nextFrame;
        }

        return this;
    }

    private Frame generateNextFrame() {
        if (frameNumber.isNextFinalFrameNumber()) {
            return FinalFrame.of(frameNumber.nextFrameNumber());
        }

        return NormalFrame.of(frameNumber.nextFrameNumber());
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NormalFrame that = (NormalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber);
    }

    @Override public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
