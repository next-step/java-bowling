package bowling_step3.domain;

import bowling_step3.domain.state.*;

public abstract class AbstractFrame implements Frame {
    private Status status;
    private final Frame nextFrame;

    public AbstractFrame() {
        this(new Ready(), new FrameGeneral());
    }

    public AbstractFrame(Frame frame) {
        this(new Ready(), frame);
    }

    public AbstractFrame(Status status, Frame nextFrame) {
        this.status = status;
        this.nextFrame = nextFrame;
    }

    public Frame play(int numPins) {
        Status status = this.status.pitch(numPins);
        this.status = status;
        if (status.isFinished()) {
            return nextFrame;
        }
        return this;
    }

    public Subtotals createSubtotals() {
        Subtotals subtotals = new Subtotals();
        accumulateResult(subtotals);
        return subtotals;
    }

    public void accumulateResult(Subtotals subtotals) {
        if (!done()) {
            return;
        }
        if (this.status instanceof Spare && nextFrame.status() instanceof Ready) {
            return;
        }
        if (this.status instanceof Strike && !nextFrame.done()) {
            return;
        }
        subtotals.add(frameResult() + subtotals.last());
        if (nextFrame != null) {
            nextFrame.accumulateResult(subtotals);
        }
    }

    Integer frameResult() {
        if (this.status instanceof Miss) {
            return this.status.getScore();
        }
        return nextFrame.calculateAdditionalScore(this.status);
    }

    public boolean done() {
        return this.status.isFinished();
    }

    public Scores scores() {
        return this.status.scores();
    }

    public int getScore() {
        return this.status.getScore();
    }

    public int calculateAdditionalScore(Status status) {
        return status.calculateAdditionalScore(this.status);
    }

    public Frame next() {
        return nextFrame;
    }

    public boolean finished() {
        return this.next() == null && this.done();
    }

    void renewStatus(Status status) {
        this.status = status;
    }

    public Status status() {
        return this.status;
    }

    @Override
    public String toString() {
        return "AbstractFrame{" +
                "status=" + status +
                ", nextFrame=" + nextFrame +
                '}';
    }
}
