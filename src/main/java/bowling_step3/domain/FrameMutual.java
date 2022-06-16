package bowling_step3.domain;

public abstract class FrameMutual implements Frame {
    private Scores scores;
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

    public Frame playManual(int numPins) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        Scores scores = this.scores.pitch(numPins);
//        this.scores = evaluateScore(scores);
//        updateSubtotal(frames);
        this.scores = scores;
        if (scores.done()) {
            return nextFrame;
        }
        return this;
//                new FrameGeneral(scores, this.subtotal);
    }

//    public void playRandom(Frames frames) {
//        Scores scores = this.scores.pitchRandom();
//        this.scores = evaluateScore(scores);
//        updateSubtotal(frames);
//    }

    abstract Scores evaluateScore(Scores scores);

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

//    public int getScore() {
//        return this.scores.getScore();
//    }

//    public Subtotal calculateAdditionalScore(Subtotal subtotal) {
//        if (subtotal.state() == State.WAIT_TWICE) {
//            return new Subtotal(State.DONE, subtotal.value() + this.scores.getScore());
//        }
//        if (subtotal.state() == State.WAIT_ONCE) {
//            return new Subtotal(State.DONE, subtotal.value() + this.scores.getFirstScore());
//        }
//        throw new UnsupportedOperationException("Cannot calculate additional yet.");
//    }

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
}
