package bowling_step3.domain;

import bowling_step3.domain.state.Ready;
import bowling_step3.domain.state.Status;

public class FrameLast extends AbstractFrame {
    public FrameLast() {
        super(new Ready(new Scores(3)), null);
    }

    @Override
    public Frame play(int numPins) {
        Status status = this.status().pitchLast(numPins);
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
}

