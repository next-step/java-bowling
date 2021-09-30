package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.exception.frame.FinalFrameCreateFrameException;

public class FinalFrame extends AbstractFrame {

    private static final int FINAL_ROUND = 10;

    private FinalFrame(Score score) {
        super(FINAL_ROUND, score);
    }

    public static Frame of(Score score) {
        return new FinalFrame(score);
    }

    @Override
    public Frame createNextFrame() {
        throw new FinalFrameCreateFrameException();
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

}
