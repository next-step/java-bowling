package bowling.model.frame;

import bowling.CannotBowlException;
import bowling.model.Score;

public interface Frame {
    int FINAL_FRAME_NO = 10;

    Frame bowl(int countOfPin) throws CannotBowlException;

    int getNo();

    boolean isFinish();

    Score calculateAdditionalScore(Score beforeScore);

    Board createBoard();

    void addFrameResult(Board board);

    FrameResult getFrameResult();
}
