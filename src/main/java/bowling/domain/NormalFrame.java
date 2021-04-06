package bowling.domain;

import bowling.dto.NormalFrameResult;

public class NormalFrame {

    private final Frame frame;

    private final FrameNumber frameNumber;

    private NormalFrame(Frame frame, FrameNumber frameNumber) {
        this.frame = frame;
        this.frameNumber = frameNumber;
    }

    public NormalFrame(int frameNumber) {
        this(new Frame(), new FrameNumber(frameNumber));
    }

    public NormalFrame(FrameNumber frameNumber) {
        this(new Frame(), frameNumber);
    }

    public static NormalFrame first() {
        return new NormalFrame(FrameNumber.first());
    }

    public static NormalFrame from(Frame frame, FrameNumber frameNumber) {
        return new NormalFrame(frame,frameNumber);
    }

    public NormalFrame next() {
        return new NormalFrame(frameNumber.next());
    }

    public void addPinCount(int pinCount) {
        if (isDone()) {
            throw new IllegalStateException("이미 끝난 프레임 입니다.");
        }
        frame.addPinCount(pinCount);
    }

    public boolean isDone() {
        return frame.isDone();
    }

    public NormalFrameResult result() {
        return new NormalFrameResult(frameNumber, frame);
    }

    public FrameNumber number() {
        return frameNumber;
    }
}
