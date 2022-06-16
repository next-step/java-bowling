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

    public Frame playManual(int numPins) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        Scores scores = this.scores().pitchLast(numPins);
        if (this.scores().sum() < 10 && this.scores().scores().size() == 2) {
            return new FrameLast(new Scores(scores.scores(), 0), null);
        }
        return new FrameLast(scores, null);
    }

    @Override
    public void playRandom(Frames frames) {

    }

    @Override
    public Subtotal subtotal() {
        return null;
    }

    @Override
    public void updateNextSubtotal(Subtotal subtotal) {

    }
}
