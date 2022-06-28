package bowling_step3.domain;

import bowling_step3.domain.state.Done;
import bowling_step3.domain.state.Ready;
import bowling_step3.domain.state.Status;

public class FrameLast extends AbstractFrame {
    public FrameLast() {
        super(new Ready(new Scores(3)), null);
    }

//    public FrameLast(Status status) {
//        super(status, null);
//    }


    @Override
    public Frame play(int numPins) {
//        if (this.done()) {
//            throw new UnsupportedOperationException("This frame is done.");
//        }
//        Scores scores = this.scores().pitchLast(numPins);
        Status status = this.status().pitchLast(numPins);

//        if (scores.sum() < 10 && scores.scores().size() == 2) {
//            return new FrameLast(new Done(new Scores(scores.scores(), 0)));
//        }
        renewStatus(status);
        return this;
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
            return this.getScore();
        }
        return null;
    }

    public int calculateAdditionalScore(Scores scores) {
        if (scores.state() == State.WAIT_TWICE && this.scores().scores().size() >= 2) {
            return scores.getScore() + this.sumOfTwo();
        }
        if (scores.state() == State.WAIT_ONCE && this.scores().scores().size() >= 1) {
            return scores.getScore() + this.getFirstScore();
        }
        throw new UnsupportedOperationException("Cannot calculate additional yet.");
    }
}
