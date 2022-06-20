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
    public Frame playManual(int numPins) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        Scores scores = this.scores().pitchLast(numPins);
//        if (scores.scores().size() == 3) {
//            return new FrameLast(scores, null);
//        }
        this.scores = scores;
        if (this.scores().sum() < 10 && this.scores().scores().size() == 2) {
            return new FrameLast(new Scores(scores.scores(), 0), null);
        }
//        if (scores.scores().size() == 3) {
//            return new FrameLast(new Scores(scores.scores(), 0), null);
//        }
        return this;
    }

    @Override
    public void playRandom(Frames frames) {

    }

    @Override
    public void updateNextSubtotal(Subtotal subtotal) {

    }

//    @Override
//    public void accumulateResult(Subtotals subtotals) {
//        subtotals.add(frameResult());
//    }

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
}
