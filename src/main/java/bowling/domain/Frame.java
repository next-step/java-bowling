package bowling.domain;

public interface Frame {
    Frame bowl(int felledPins);

    int getFrameNum();

    String desc();

    Score getScore();

    Score calculateAdditionalScore(Score beforeScore);

    void addFrameResult(Board board);
}
