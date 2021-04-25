package bowling.domain.engine.frame;

import bowling.domain.concrete.FinalFrame;
import bowling.domain.concrete.NormalFrame;

public class FrameCreator {

    private static final int MAX_FRAMES = 10;

    private int count = 1;

    public Frame create() {
        return count++ < MAX_FRAMES ? new NormalFrame() : new FinalFrame();
    }

}
