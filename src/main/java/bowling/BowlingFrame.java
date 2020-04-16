package bowling;

public interface BowlingFrame {

    void bowl(int scoreCount);

    boolean isOver();

    int sum();

    SubTotal getSubTotal(final NextAddingUpScores nextAddingUpScores);

    FrameScore getFrameScore();
}
