package bowling.domain;

import bowling.engine.Frame;
import bowling.engine.Result;
import bowling.engine.Shot;

public class FinalFrame extends NormalFrame {
    protected FinalFrame(Result result) {
        super(FrameSequence.FINAL_FRAME, FinalFrameResult.ofFinal(result));
    }

    public static Frame of(Result result) {
        return new FinalFrame(result);
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (completed()) {
            throw new IllegalStateException("the game is ended");
        }

        return of(result.next(shot));
    }

    @Override
    public boolean isFinal() {
        return true;
    }


    @Override
    public String toString() {
        return "FinalNormalFrame{" +
                "result=" + result +
                '}';
    }
}
