package bowling;

public interface BowlingFrame {

    void bowl(int scoreCount);

    boolean isOver();

    int sum();

    FrameScoreResult getResult();

    SubTotal calculateSubTotal(final NextAddingUpScores nextAddingUpScores);

    FrameScore getFrameScore();
}
