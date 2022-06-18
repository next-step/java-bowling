package bowling_step3.domain;

public interface Frame {
    Frame playManual(int numPins);

    void playRandom(Frames frames);

    Integer subtotal(Subtotals subtotals);

    void updateNextSubtotal(Subtotal subtotal);

    Scores scores();

//    void updateSubtotal(Frames frames);

    int getScore();

    int calculateAdditionalScore(Scores scores);

    boolean done();

    Frame next();

    State state();

    Subtotals createSubtotals();

    void accumulateResult(Subtotals subtotals);

//    Subtotal getSubtotal(Frames frames);

//    Frame next(Frames frames);
}
