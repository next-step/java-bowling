package bowling.model.Frame;

import bowling.CannotBowlException;

public class FinalFrame implements Frame{
    @Override
    public Frame bowl(int countOfPin) throws CannotBowlException {
        return null;
    }

    @Override
    public int getNo() {
        return FINAL_FRAME_NO;
    }
}
