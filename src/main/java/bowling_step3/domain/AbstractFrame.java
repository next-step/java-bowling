package bowling_step3.domain;

import bowling_step3.domain.state.Ready;
import bowling_step3.domain.state.Status;

public abstract class AbstractFrame implements Frame {
    //    private Scores scores;
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
//        if (this.done()) {
//            throw new UnsupportedOperationException("This frame is done.");
//        }
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
        if (this.state() == State.WAIT_ONCE && nextFrame.scores().scores().size() < 1) {
            return;
        }
        if (this.state() == State.WAIT_TWICE && !nextFrame.done()) {
            return;
        }
        subtotals.add(frameResult() + subtotals.last());
        if (nextFrame != null) {
            nextFrame.accumulateResult(subtotals);
        }
    }

    Integer frameResult() {
        if (this.status.isFinished()) {
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
        return status.calculateAdditionalScore(status);

//        if (scores.state() == State.WAIT_TWICE && this.scores.getFirstScore() == 10) {
//            return 30;
//        }
//        if (scores.state() == State.WAIT_TWICE) {
//            return scores.getScore() + this.scores.sumOfTwo();
//        }
//        if (scores.state() == State.WAIT_ONCE && this.scores.scores().size() >= 1) {
//            return scores.getScore() + this.scores.getFirstScore();
//        }
//        throw new UnsupportedOperationException("Cannot calculate additional yet.");
    }

    public Frame next() {
        return nextFrame;
    }

    public State state() {
        return this.status.scores().state();
    }

    public boolean finished() {
        return this.next() == null && this.done();
    }

    protected Integer sumOfTwo() {
        return this.status.scores().sumOfTwo();
    }

    protected Integer getFirstScore() {
        return this.status.scores().getFirstScore();
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
