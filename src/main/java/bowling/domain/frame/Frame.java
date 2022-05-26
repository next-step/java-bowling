package bowling.domain.frame;

import bowling.domain.bowl.Bowls;

public abstract class Frame {
    protected final FrameNumber frameNumber;
    protected final Bowls bowls;

    public Frame(FrameNumber frameNumber, Bowls bowls) {
        validate(frameNumber, bowls);
        this.bowls = bowls;
        this.frameNumber = frameNumber;
    }

    private void validate(FrameNumber frameNumber, Bowls bowls) {
        if (frameNumber == null) {
            throw new IllegalArgumentException("frameNumber는 null 일 수 없습니다.");
        }
        if (bowls == null) {
            throw new IllegalArgumentException("bowls는 null 일 수 없습니다.");
        }
    }

    public static NormalFrame initialize() {
        return new NormalFrame(FrameNumber.min(), Bowls.initialize());
    }

    public abstract Frame next();

    public abstract boolean isFinal();

    @Override
    public String toString() {
        return bowls.toString();
    }
}
