package bowling.domain.frame;

import bowling.domain.Pins;

import java.util.LinkedList;

public class NormalFrame implements Frame {

    private final FrameNumber frameNumber;
    private final Pins pitchPins;

    public NormalFrame(final int frameNumber) {
        this.frameNumber = new FrameNumber(frameNumber);
        this.pitchPins = new Pins(new LinkedList<>());
    }

    @Override
    public boolean isEnd() {
        return pitchPins.isEnd(frameNumber);
    }

    @Override
    public void pitch(final int countOfPins) {
        pitchPins.pitch(countOfPins, frameNumber);
    }

    @Override
    public String valueOfFrame() {
        return pitchPins.result();
    }
}
