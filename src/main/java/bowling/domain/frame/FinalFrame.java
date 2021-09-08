package bowling.domain.frame;


import bowling.domain.Pins;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class FinalFrame implements Frame {
    public static final int FINAL_FRAME_NUMBER = 10;

    private final FrameNumber frameNumber;
    private final Pins pitchPins;

    public FinalFrame() {
        this.frameNumber = new FrameNumber(FINAL_FRAME_NUMBER);
        this.pitchPins = new Pins(new LinkedList<>());
    }

    public FinalFrame(final List<Integer> pins) {
        this.frameNumber = new FrameNumber(FINAL_FRAME_NUMBER);
        this.pitchPins = new Pins(pins);
    }

    @Override
    public boolean isEnd() {
        return pitchPins.isEnd(frameNumber);
    }

    @Override
    public Pins pitch(final int countOfPins) {
        return pitchPins.pitch(countOfPins, frameNumber);
    }

    @Override
    public String valueOfFrame() {
        return pitchPins.result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinalFrame that = (FinalFrame) o;
        return Objects.equals(frameNumber, that.frameNumber) &&
                Objects.equals(pitchPins, that.pitchPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber, pitchPins);
    }
}
