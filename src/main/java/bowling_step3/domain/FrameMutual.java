package bowling_step3.domain;

public abstract class FrameMutual implements Frame {
    Scores scores;
    private Frame nextFrame;

    public FrameMutual(Scores scores, Frame nextFrame) {
        this.scores = scores;
        this.nextFrame = nextFrame;
    }

    public FrameMutual() {
        this(new Scores(), new FrameGeneral());
    }

    public FrameMutual(Frame frame) {
        this(new Scores(), frame);
    }

    public Frame play(int numPins) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        Scores scores = this.scores.pitch(numPins);
        this.scores = scores;
        if (scores.done()) {
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
        if (scores.state() == State.DONE) {
            return this.scores.getScore();

        }
        return nextFrame.calculateAdditionalScore(scores);
    }

    public boolean done() {
        return this.scores.done();
    }

    public Scores scores() {
        return this.scores;
    }

    public int getScore() {
        return this.scores.getScore();
    }

    public Integer subtotal(Subtotals subtotals) {
        if (this.scores.state() == State.DONE) {
            return subtotals.sum() + this.scores.getScore();
        }
        return subtotals.sum() + nextFrame.calculateAdditionalScore(this.scores);
    }

    public int calculateAdditionalScore(Scores scores) {
        if (scores.state() == State.WAIT_TWICE && this.scores.getFirstScore() == 10) {
            return 30;
        }
        if (scores.state() == State.WAIT_TWICE) {
            return scores.getScore() + this.scores.sumOfTwo();
        }
        if (scores.state() == State.WAIT_ONCE && this.scores.scores().size() >= 1) {
            return scores.getScore() + this.scores.getFirstScore();
        }
        throw new UnsupportedOperationException("Cannot calculate additional yet.");
    }

    public Frame next() {
        return nextFrame;
    }

    public State state() {
        return this.scores.state();
    }

    @Override
    public String toString() {
        return "FrameMutual{" +
                "scores=" + scores +
                ", nextFrame=" + nextFrame +
                '}';
    }
}
