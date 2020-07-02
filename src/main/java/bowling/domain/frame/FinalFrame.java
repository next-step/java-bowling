package bowling.domain.frame;

import bowling.domain.FallenPinNumber;
import bowling.domain.pitching.Pitching;
import bowling.domain.pitching.StandbyPitching;

import java.util.Objects;

public class FinalFrame implements Frame {

    private FrameNumber frameNumber;
    private Pitching pitching;

    private FinalFrame(FrameNumber frameNumber) {
        this.frameNumber = frameNumber;
        this.pitching = new StandbyPitching();
    }

    public static FinalFrame of(FrameNumber frameNumber) {
        return new FinalFrame(frameNumber);
    }

    @Override
    public Frame figureOutFrame(FallenPinNumber fallenPinNumber) {
        Pitching action = pitching.pitch(fallenPinNumber);
        if (action.isFinished(this)) {
            pitching = action;
            return null;
        }
        pitching = action;
        return this;
    }

    @Override
    public boolean isFinalFrame() {
        return true;
    }

    @Override
    public Pitching getPitching() {
        return pitching;
    }

    @Override
    public Frame getNextFrame() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(pitching, that.pitching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, pitching);
    }
}
