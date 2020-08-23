package bowling.domian.frame;

public interface Frame {
    int getFrameNumber();

    Frame bowl(int falledPinsCount);

    Score calculateAdditional(Score score);

    boolean isGameEnd();

    Board addBoard(Board board);
}
