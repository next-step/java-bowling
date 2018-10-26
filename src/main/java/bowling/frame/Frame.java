package bowling.frame;

public interface Frame {
    Frame bowl(int countOfPin);

    int getNo();

    Score calculateAdditionalScore(Score beforeScore);

    void addFrameResult(Board board);

    Board createBoard();
}
