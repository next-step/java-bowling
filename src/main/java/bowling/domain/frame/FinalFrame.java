package bowling.domain.frame;


import bowling.domain.Pins;

import java.util.LinkedList;

public class FinalFrame implements Frame {
    private static final int FINAL_FRAME_NUMBER = 10;

    private final FrameNumber frameNumber;
    private final Pins pitchPins;

    public FinalFrame() {
        this.frameNumber = new FrameNumber(FINAL_FRAME_NUMBER);
        this.pitchPins = new Pins(new LinkedList<>());
    }

    @Override
    public boolean isEnd() {
        return pitchPins.isEnd(frameNumber);
    }

    @Override
    public void pitch(final int countOfPins) {
        Pins pins = pitchPins.pitch(countOfPins, frameNumber);
    }

    @Override
    public String valueOfFrame() {
        return null;
    }
}
