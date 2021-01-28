package bowling.domain.frame;

import bowling.domain.frame.status.Start;
import bowling.domain.frame.status.Status;

public class NormalFrame extends Frame {

    private Status status;

    public NormalFrame(int frameNumber) {
        super(frameNumber);
        status = new Start();
    }

    @Override
    public Frame record(int downedPin) {
        if (isEnd()) {
            Frame next = createNextFrame();
            next.record(downedPin);
            return next;
        }

        status = status.record(downedPin);

        return this;
    }

    private Frame createNextFrame() {
        int nextFrameNumber = frameNumber + 1;

        if (nextFrameNumber == lastFrame) {
            return new LastFrame();
        }

        return new NormalFrame(nextFrameNumber);
    }

    @Override
    public boolean isEnd() {
        return status.isEnd();
    }

    @Override
    public String getDescriptionForm() {
        return status.getDescription();
    }

    public int calculateScore() {
        return status.calculateScore();
    }
}
