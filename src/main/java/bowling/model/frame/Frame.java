package bowling.model.frame;

import bowling.model.CannotBowlException;
import bowling.model.Score;

public interface Frame {
    int FINAL_FRAME_NO = 10;
    int UN_SCORE_STATE = -1;

    Frame bowl(int countOfPin) throws CannotBowlException;

    int getNo();

    Score calculateAdditionalScore(Score beforeScore);

    Board createBoard();

    void addFrameResult(Board board);

    boolean isEndGame();
}
