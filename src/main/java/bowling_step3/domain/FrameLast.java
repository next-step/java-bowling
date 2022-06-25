package bowling_step3.domain;

public class FrameLast extends AbstractFrame {
    public FrameLast() {
        super(new Scores(3), null);
    }

    public FrameLast(Scores scores) {
        super(scores, null);
    }

    @Override
    public Frame play(int numPins) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        Scores scores = this.scores().pitchLast(numPins);
//        this.scores = scores;
//        Frame newFrame = new FrameLast(scores);
        if (scores.sum() < 10 && scores.scores().size() == 2) {
//            this.scores =
            return new FrameLast(new Scores(scores.scores(), 0));
        }
        return new FrameLast(scores);
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

