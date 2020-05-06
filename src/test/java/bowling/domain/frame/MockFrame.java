package bowling.domain.frame;

import bowling.domain.frame.score.DefaultFrameScore;
import bowling.domain.frame.score.FrameScore;
import bowling.domain.shot.Shot;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class MockFrame implements Frame {
    private final int frameNumber;
    private final boolean isFrameSet;
    private final FrameScore frameScore;
    private int shotSize;

    public MockFrame(int frameNumber, boolean isFrameSet, FrameScore frameScore, int shotSize) {
        this.frameNumber = frameNumber;
        this.isFrameSet = isFrameSet;
        this.frameScore = frameScore;
        this.shotSize = shotSize;
    }

    public MockFrame(int frameNumber) {
        this(frameNumber, false, DefaultFrameScore.NULL, 0);
    }

    public MockFrame(int frameNumber, int shotSize) {
        this(frameNumber, false, DefaultFrameScore.NULL, shotSize);
    }

    public MockFrame(boolean isFrameSet, FrameScore frameScore) {
        this(1, true, frameScore, 0);
    }

    public MockFrame(int frameNumber, boolean isFrameSet) {
        this(frameNumber, isFrameSet, DefaultFrameScore.NULL, 0);
    }

    @Override
    public Frame next() {
        return new MockFrame(frameNumber + 1);
    }

    @Override
    public boolean isFrameSet() {
        return isFrameSet;
    }

    @Override
    public List<Shot> shots() {
        throw new NotImplementedException();
    }

    @Override
    public FrameScore getFrameScore() {
        return frameScore;
    }

    @Override
    public void shot(int shot) {
        shotSize++;
    }

    @Override
    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public int getShotsCount() {
        return shotSize;
    }

    @Override
    public FrameScore addBonus(FrameScore beforeScore) {
        return null;
    }
}
