package bowling.domain.engine.frame;

import bowling.domain.concrete.frame.FinalFrame;
import bowling.domain.concrete.frame.NormalFrame;

public class FrameCreator {

    private static final int MAX_FRAMES = 10;

    private int count = 1;

    public Frame create() {
        return count++ < MAX_FRAMES ? NormalFrame.init() : FinalFrame.init();
    }

}
