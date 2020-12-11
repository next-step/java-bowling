package bowling.domain.frame;

import bowling.domain.KnockDownPins;
import bowling.domain.pitchings.NormalFramePitchings;
import bowling.domain.pitchings.Pitchings;

public class NormalFrame implements Frame {
    private final int index;
    private final NormalFramePitchings pitchings;
    private Frame nextFrame;

    private NormalFrame(int index) {
        this.index = index;
        pitchings = NormalFramePitchings.getInstance();
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
    public Frame getNextFrame() {
        return nextFrame;
    }

    @Override
    public void setKnockDownPins(KnockDownPins knockDownPins) {
        pitchings.addPitching(knockDownPins);
    }

    @Override
    public Pitchings getPitchings(){
        return pitchings;
    }

    @Override
    public boolean isEnd() {
       return pitchings.isEnd();
    }

    @Override
    public boolean isLastFrame() {
        return false;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "index=" + index +
                ", pitchings=" + pitchings +
                '}';
    }
}
