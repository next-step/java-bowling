package bowling_step3.domain;

public interface Frame {
    Frame playManual(int numPins, Frames frames);

    void playRandom(Frames frames);

    Subtotal subtotal();

    void updateNextSubtotal(Subtotal subtotal);

    Scores scores();

//    void updateSubtotal(Frames frames);

//    int getScore();

//    Subtotal calculateAdditionalScore(Subtotal subtotal);

    boolean done();

    Frame next();

    State state();

//    Subtotal getSubtotal(Frames frames);

//    Frame next(Frames frames);
}
