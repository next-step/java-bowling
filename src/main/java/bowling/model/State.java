package bowling.model;

import bowling.CannotBowlException;

public interface State {
    int FINAL_FRAME_NO = 10;
    State bowl(int countOfPin) throws CannotBowlException;
    boolean isFinish(int frameNo);
}
