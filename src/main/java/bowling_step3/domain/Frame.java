package bowling_step3.domain;

import bowling_step3.domain.state.Status;

public interface Frame {
    Frame play(int numPins);

    Scores scores();

    int calculateAdditionalScore(Status status);

    Frame next();

    Subtotals createSubtotals();

    void accumulateResult(Subtotals subtotals);

    Status status();
}
