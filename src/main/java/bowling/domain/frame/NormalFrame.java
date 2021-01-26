package bowling.domain.frame;

import bowling.domain.frame.status.Start;
import bowling.domain.frame.status.Status;

public class NormalFrame extends Frame {

    private Status status;

    public NormalFrame() {
        status = new Start();
    }

    @Override
    public Frame record(int downedPin) {
        if (isEnd()) {
            NormalFrame next = new NormalFrame();
            next.record(downedPin);
            return next;
        }

        status = status.record(downedPin);

        return this;
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
