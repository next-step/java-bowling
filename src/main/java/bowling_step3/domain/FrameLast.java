package bowling_step3.domain;

public class FrameLast extends AbstractFrame {
    public FrameLast() {
        super(new Scores(3), null);
    }

    @Override
    public Frame play(int numPins) {
        if (this.done()) {
            throw new UnsupportedOperationException("This frame is done.");
        }
        Scores scores = this.scores().pitchLast(numPins);
        this.scores = scores;
        if (this.scores().sum() < 10 && this.scores().scores().size() == 2) {
            this.scores = new Scores(scores.scores(), 0);
        }
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
            return this.scores.getScore();
        }
        return null;
    }

    public int calculateAdditionalScore(Scores scores) {
        if (scores.state() == State.WAIT_TWICE && this.scores.scores().size() >= 2) {
            return scores.getScore() + this.scores.sumOfTwo();
        }
        if (scores.state() == State.WAIT_ONCE && this.scores.scores().size() >= 1) {
            return scores.getScore() + this.scores.getFirstScore();
        }
        throw new UnsupportedOperationException("Cannot calculate additional yet.");
    }
}
