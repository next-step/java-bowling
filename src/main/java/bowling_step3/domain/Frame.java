package bowling_step3.domain;

public interface Frame {
    Frame play(int numPins);

    Integer subtotal(Subtotals subtotals);

    Scores scores();

    int getScore();

    int calculateAdditionalScore(Scores scores);

    boolean done();

    Frame next();

    State state();

    Subtotals createSubtotals();

    void accumulateResult(Subtotals subtotals);
}
