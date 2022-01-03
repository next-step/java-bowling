package bowling.domain.frame;

import bowling.domain.result.FinalFrameResult;
import bowling.domain.FrameSequence;
import bowling.engine.Frame;
import bowling.engine.Result;
import bowling.engine.Shot;

public class FinalFrame extends BowlingFrame {
    protected FinalFrame(Result result) {
        super(FrameSequence.FINAL_FRAME, FinalFrameResult.ofFinal(result));
    }

    public static Frame of(Result result) {
        return new FinalFrame(result);
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (result.completed()) {
            throw new IllegalStateException("the game is ended");
        }

        return ofFinal(result.next(shot));
    }

    @Override
    public boolean isFinal() {
        return true;
    }
}
