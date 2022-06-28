package bowling_step3.domain;

public abstract class AbstractFrame implements Frame {
    private Scores scores;
    private final Frame nextFrame;

    public AbstractFrame() {
        this(new Scores(), new FrameGeneral());
    }

    public AbstractFrame(Frame frame) {
        this(new Scores(), frame);
    }

    public AbstractFrame(Scores scores, Frame nextFrame) {
        this.scores = scores;
        this.nextFrame = nextFrame;
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

    public boolean finished() {
        return this.next() == null && this.done();
    }

    @Override
    public String toString() {
        return "FrameMutual{" +
                "scores=" + scores +
                ", nextFrame=" + nextFrame +
                '}';
    }

    protected Integer sumOfTwo() {
        return this.scores.sumOfTwo();
    }

    protected Integer getFirstScore() {
        return this.scores.getFirstScore();
    }

    void renewScores(Scores scores) {
        this.scores = scores;
    }
}
