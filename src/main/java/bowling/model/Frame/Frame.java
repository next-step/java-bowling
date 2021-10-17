package bowling.model.frame;

import bowling.CannotBowlException;

public interface Frame {
    int FINAL_FRAME_NO = 10;

    Frame bowl(int countOfPin) throws CannotBowlException;

    int getNo();

    boolean isFinish();

}
