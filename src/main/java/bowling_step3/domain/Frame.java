package bowling_step3.domain;

import bowling_step3.domain.state.Status;

public interface Frame {
    Frame play(int numPins);

    Scores scores();

    int getScore();

    int calculateAdditionalScore(Status status);

    boolean done();

    Frame next();

    Subtotals createSubtotals();

    void accumulateResult(Subtotals subtotals);

    boolean finished();

    Status status();
}
