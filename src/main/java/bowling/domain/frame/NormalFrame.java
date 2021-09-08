package bowling.domain.frame;

import bowling.domain.Pins;

import java.util.LinkedList;
import java.util.List;

public class NormalFrame implements Frame {

    private final FrameNumber frameNumber;
    private final Pins pitchPins;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitchPins = new Pins(new LinkedList<>());
    }

    public NormalFrame(final int frameNumber, final List<Integer> pins) {
        this.frameNumber = new FrameNumber(frameNumber);
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
}
