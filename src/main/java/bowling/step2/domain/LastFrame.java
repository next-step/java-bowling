package bowling.step2.domain;

import bowling.step2.domain.visitor.FrameVisitor;

import java.util.List;

public class LastFrame implements Frame {
    private final int frameNo;

    private final PitchGroup pitchGroup;

    private static final int MAX_PITCH_SIZE = 3;

    private LastFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pitchGroup = PitchGroup.of(MAX_PITCH_SIZE);
    }

    public static LastFrame of(int frameNo) {
        return new LastFrame(frameNo);
    }

    @Override
    public void pitch(int count) {
        pitchGroup.pitch(count);
    }


    @Override
    public Frame nextFrame(FrameVisitor frameVisitor) {
        return frameVisitor.nextFrame(this);
    }

    @Override
    public boolean finished() {
        return pitchGroup.finished();
    }

    @Override
    public List<Integer> current() {
        return pitchGroup.pitches();
    }
}
