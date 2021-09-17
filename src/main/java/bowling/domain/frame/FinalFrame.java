package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.exception.frame.FinalFrameCreateException;

public class FinalFrame extends Frame {

    private FinalFrame(int round, Score score) {
        super(round, score);
    }

    public static Frame of(int round, Score score) {
        return new FinalFrame(round, score);
    }

    @Override
    public Frame createNextFrame() {
        throw new FinalFrameCreateException();
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

}
