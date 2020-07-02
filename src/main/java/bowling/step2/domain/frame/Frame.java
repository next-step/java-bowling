package bowling.step2.domain.frame;

import bowling.step2.domain.pitch.Pitches;

public abstract class Frame {

    protected int frameNo;
    protected final Pitches pitches;

    public Frame(int frameNo) {
        validateFrameNo(frameNo);
        this.frameNo = frameNo;
        this.pitches = new Pitches();
    }

    public Frame(int frameNo, Pitches pitches) {
        this.frameNo = frameNo;
        this.pitches = pitches;
    }

    abstract void validateFrameNo(int frameNo);

    public abstract Frame pitch(int pitch);

    public abstract boolean pitchesOver();

    public abstract Frame nextFrame();
}
