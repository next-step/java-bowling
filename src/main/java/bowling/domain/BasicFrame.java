package bowling.domain;

import bowling.Validator;

public class BasicFrame extends Frame {

    public BasicFrame(int frameNumber) {
        super(frameNumber);
    }

    @Override
    public void secondTry(int score) {
        super.secondTry(score);
        Validator.checkFrameHitPinCount(getScore1().getScore(), getScore2().getScore());
    }

    public Frame nextFrame() {
        if (getFrameNumber() + 1 == FINAL_FRAME_NUMBER) {
            return new FinalFrame(getFrameNumber() + 1);
        }
        return new BasicFrame(getFrameNumber() + 1);
    }
}
