package bowling_step3.domain;

public class FrameLast extends FrameMutual implements Frame {
    public FrameLast() {
        super(new Scores(3), null);
    }

    public FrameLast(Scores scores, Frame next) {
        super(scores, null);
    }

    protected Scores evaluateScore(Scores scores) {
        return scores.evaluateLastBonus();
    }

    protected State evaluateState(Scores scores) {
        if (scores.done()) {
            return State.DONE;
        }
        return State.INIT;
    }

//    public Subtotal calculateAdditionalScore(Subtotal subtotal) {
//        if (subtotal.state() == State.WAIT_TWICE) {
//            return new Subtotal(State.DONE, subtotal.value() + getAdditionalForStrike());
//        }
//        if (subtotal.state() == State.WAIT_ONCE) {
//            return new Subtotal(State.DONE, subtotal.value() + getFirstScore());
//        }
//        throw new UnsupportedOperationException("Cannot calculate additional yet.");
//    }

    //    public Frame playManual(int numPins, Frames frames) {
//        if (this.done()) {
//            throw new UnsupportedOperationException("This frame is done.");
//        }
//        Scores scores = this.scores().pitch(numPins);
////        this.scores = evaluateScore(scores);
//        return new FrameLast(scores, this.subtotal());
//    }
    @Override
    public Frame play(int numPins) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        Scores scores = this.scores().pitchLast(numPins);
//        Scores scores = this.scores().pitch(numPins);
        this.scores = scores;
//        if (this.scores().sum() == 20 && this.scores().scores().size() == 2) {
//            this.scores = new Scores(scores.scores(), scores.remainingPitch() + 1);
//        }
        if (this.scores().sum() < 10 && this.scores().scores().size() == 2) {
            this.scores = new Scores(scores.scores(), 0);
        }
        return this;
    }

    @Override
    public Frame play(Scores scores) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        this.scores = scores;
        if (this.scores().sum() == 20 && this.scores().scores().size() == 2) {
            this.scores = new Scores(scores.scores(), scores.remainingPitch() + 1);
        }
        if (this.scores().sum() < 10 && this.scores().scores().size() == 2) {
            this.scores = new Scores(scores.scores(), 0);
        }
        return this;
    }

    public Frame playRandom() {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
//        Scores scores = this.scores().pitchLast(numPins);
        Scores scores = this.scores.pitchRandom();
        this.scores = scores;
        if (this.scores().sum() < 10 && this.scores().scores().size() == 2) {
            return new FrameLast(new Scores(scores.scores(), 0), null);
        }
        return this;
    }

    @Override
    public void updateNextSubtotal(Subtotal subtotal) {

    }

    @Override
    public void accumulateResult(Subtotals subtotals) {
        if (!done()) {
            return;
        }
        subtotals.add(frameResult() + subtotals.last());
    }

    @Override
    Integer frameResult() {
        if (this.done()) {
            return this.scores.getScore();
        }
        return null;
        // todo
//        System.out.println(this);
//        if (scores.state() == State.DONE) {
//            System.out.println(scores);
//            return this.scores.getScore();
//
//        }
//        System.out.println(this.scores);
//        return nextFrame.calculateAdditionalScore(scores);
    }

    public int calculateAdditionalScore(Scores scores) {
//        if (scores.state() == State.WAIT_TWICE && this.scores.getFirstScore() == 10) {
//            return 30;
//        }
        if (scores.state() == State.WAIT_TWICE && this.scores.scores().size() >= 2) {
            return scores.getScore() + this.scores.sumOfTwo();
        }
        if (scores.state() == State.WAIT_ONCE && this.scores.scores().size() >= 1) {
            return scores.getScore() + this.scores.getFirstScore();
        }
        throw new UnsupportedOperationException("Cannot calculate additional yet.");
    }
}
