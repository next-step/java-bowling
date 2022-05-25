package bowling.domain.frame;

public class Frame {
    private final FrameNumber frameNumber;

    public Frame(FrameNumber frameNumber) {
        validate(frameNumber);
        this.frameNumber = frameNumber;
    }

    private void validate(FrameNumber frameNumber) {
        if (frameNumber == null) {
            throw new IllegalArgumentException("frameNumber는 null 일 수 없습니다.");
        }
    }

    public static Frame initialize() {
        return new Frame(FrameNumber.min());
    }

    public Frame next() {
        return new Frame(frameNumber.next());
    }

    public boolean isLast() {
        return frameNumber.isLast();
    }
}
