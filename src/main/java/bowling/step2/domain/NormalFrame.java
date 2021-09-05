package bowling.step2.domain;

import bowling.step2.domain.visitor.FrameVisitor;

import java.util.List;

public class NormalFrame implements Frame {
    private final int frameNo;

    private final PitchGroup pitchGroup;

    private static final int MAX_PITCH_SIZE = 2;

    private static final int MAX_NORMAL_FRAME_NUM = 9;

    private NormalFrame(int frameNo) {
        this.frameNo = frameNo;
        this.pitchGroup = PitchGroup.of(MAX_PITCH_SIZE);
    }

    public static NormalFrame of(int frameNo) {
        return new NormalFrame(frameNo);
    }

    @Override
    public void pitch(int count) {
        pitchGroup.pitch(count);
    }

    @Override
    public Frame nextFrame(FrameVisitor frameVisitor) {
        return frameVisitor.nextFrame(this);
    }

    public boolean isLastOfNormalFrame() {
        return this.frameNo == MAX_NORMAL_FRAME_NUM;
    }

    @Override
    public boolean finished() {
        return pitchGroup.finished();
    }

    @Override
    public List<Integer> current() {
        return pitchGroup.pitches();
    }

    public int frameNo() {
        return this.frameNo;
    }
}
