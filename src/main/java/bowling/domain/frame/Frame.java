package bowling.domain.frame;

import bowling.domain.bowl.Bowls;

public class Frame {
    private final FrameNumber frameNumber;
    private final Bowls bowls;

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

    public static Frame initialize() {
        return new Frame(FrameNumber.min(), Bowls.initialize());
    }

    public Frame next() {
        return new Frame(frameNumber.next(), Bowls.initialize());
    }

    public boolean isLast() {
        return frameNumber.isLast();
    }
}
