package bowling.domain.frame;

import bowling.domain.engine.Frame;

import static bowling.domain.Constants.FRAME_TRIGGER_LAST_ROUND;

public class FrameFactory {

    public static NormalFrame first() {
        return new NormalFrame();
    }

    public static Frame next(final int round) {
        if (round == FRAME_TRIGGER_LAST_ROUND) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }
}
