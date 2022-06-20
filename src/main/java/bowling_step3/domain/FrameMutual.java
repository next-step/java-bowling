package bowling_step3.domain;

public abstract class FrameMutual implements Frame {
    Scores scores;
    private Frame nextFrame;
//    private Subtotal subtotal;

    public FrameMutual(Scores scores, Frame nextFrame) {
        this.scores = scores;
        this.nextFrame = nextFrame;
//        this.subtotal = subtotal;
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
    public Frame play(Scores scores) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        this.scores = scores;
        if (scores.done()) {
            return nextFrame;
        }
        return this;
    }

    public Frame playRandom() {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        Scores scores = this.scores.pitchRandom();
        this.scores = scores;
        if (scores.done()) {
            return nextFrame;
        }
        return this;
    }
//    public void playRandom(Frames frames) {
//        Scores scores = this.scores.pitchRandom();
//        this.scores = evaluateScore(scores);
//        updateSubtotal(frames);
//    }

    abstract Scores evaluateScore(Scores scores);

    public Subtotals createSubtotals() {
        Subtotals subtotals = new Subtotals();
        accumulateResult(subtotals);
        return subtotals;
    }

    public void accumulateResult(Subtotals subtotals) {
//        Integer result = frameResult();
        if (!done() ) {
            return;
        }
        if(this.state() == State.WAIT_ONCE && nextFrame.scores().scores().size() < 1) {
            return;
        }
        if(this.state() == State.WAIT_TWICE && !nextFrame.done()){
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
//    public void updateSubtotal(Frames frames) {
//        if (this.scores.done()) {
//            State state = evaluateState(this.scores);
//            this.subtotal = new Subtotal(state, this.subtotal.value() + this.scores.sum());
//        }
//        if (frames.index(this) > 0 && frames.prev(this).subtotal().state().waiting()) {
//            frames.prev(this).subtotal().accumulateBonus(this.scores.lastScore());
//            this.subtotal = new Subtotal(this.subtotal.state(), this.subtotal.value() + this.scores.lastScore());
//        }
//    }

//    abstract State evaluateState(Scores scores);


//    public Subtotal subtotal() {
//        return this.subtotal;
//    }

    public boolean done() {
        return this.scores.done();
    }

    public Scores scores() {
        return this.scores;
    }

//    public void updateNextSubtotal(Subtotal subtotal) {
//        this.subtotal = new Subtotal(State.INIT, subtotal.value());
//    }

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
        if (scores.state() == State.WAIT_TWICE) {
            return scores.getScore() + this.scores.sumOfTwo();
        }
        if (scores.state() == State.WAIT_ONCE && this.scores.scores().size() >= 1) {
            return scores.getScore() + this.scores.getFirstScore();
        }
        throw new UnsupportedOperationException("Cannot calculate additional yet.");
    }

//    public Subtotal getSubtotal(Frames frames) {
//        return new Subtotal(State.DONE, this.subtotal().value() + frames.next(this).subtotal().value());
//    }

    public int getAdditionalForStrike() {
        return this.scores.getAdditionalForStrike();
    }

    public int getFirstScore() {
        return this.scores.getFirstScore();
    }

    public Frame next() {
        return nextFrame;
    }
//    public Frame next(Frames frames) {
//        return nextFrame;
//    }


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
