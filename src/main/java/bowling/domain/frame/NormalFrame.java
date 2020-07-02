package bowling.domain.frame;

import bowling.domain.status.Status;
import bowling.domain.status.running.Ready;

public class NormalFrame implements Frame {
    public static final int LAST_NORMAL_FRAME = 9;

    private Status status;
    private Frame next;

    private NormalFrame() {
        this.status = new Ready();
    }

    public static Frame init() {
        return new NormalFrame();
    }

    public Status bowl(int downPin) {
        this.status = status.bowl(downPin);
        return status;
    }

    @Override
    public Frame nextFrame(int index) {
        this.next = getNextFrame(index);
        return next;
    }

    public String printFrameResult() {
        return status.printResult();
    }

    @Override
    public boolean canPlayMore() {
        return this.status.canPlayMore();
    }

    private Frame getNextFrame(int index) {
        if (index == LAST_NORMAL_FRAME) {
            return FinalFrame.init();
        }
        return new NormalFrame();
    }

    @Override
    public String toString() {
        return "NormalFrame{" +
                "next=" + next +
                ", status=" + status.printResult() +
                '}';
    }
}
