package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.pitchings.NormalFramePitchings;
import bowling.domain.pitchings.Pitchings;

public class NormalFrame extends Frame {
    private final int index;
    private Frame nextFrame;

    private NormalFrame(int index) {
        super(NormalFramePitchings.getInstance());
        this.index = index;
    }

    public static Frame getFirstFrame() {
        return new NormalFrame(1);
    }

    @Override
    public Frame initNextFrame() {
        int nextFrameIndex = index + 1;
        int lastFrameIndex = Frames.MAX_FRAME_SIZE;
        if (nextFrameIndex == lastFrameIndex) {
            nextFrame = LastFrame.getInstance(lastFrameIndex);
            return nextFrame;
        }

        nextFrame = new NormalFrame(nextFrameIndex);
        return nextFrame;
    }

    @Override
    public Frame setKnockDownPins(KnockDownPins knockDownPins) {
        Pitchings pitchings = super.getPitchings();
        pitchings.addPitching(knockDownPins);
        if (pitchings.isEnd()) {
            return nextFrame;
        }
        return this;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pitchings=" + super.getPitchings() +
                '}';
    }
}
