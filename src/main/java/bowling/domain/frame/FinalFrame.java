package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import bowling.exception.frame.FinalFrameCreateException;

public class FinalFrame extends Frame {

    FinalFrame(int round, Score score) {
        super(round, score);
    }

    @Override
    public Frame createNextFrame() {
        throw new FinalFrameCreateException();
    }

    @Override
    public void updateScoreByPin(Pin pin) {
        score = score.nextPin(pin);
    }
}
