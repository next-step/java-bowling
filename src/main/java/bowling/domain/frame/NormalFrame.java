package bowling.domain.frame;

import bowling.domain.frame.status.Start;
import bowling.domain.frame.status.Status;

public class NormalFrame extends Frame {

    private Status status;

    public NormalFrame() {
        status = new Start();
    }

    @Override
    public void record(int downedPin) {
        status = status.record(downedPin);
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
