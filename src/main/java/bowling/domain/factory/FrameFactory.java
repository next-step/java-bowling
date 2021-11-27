package bowling.domain.factory;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.value.FrameNumber;

public class FrameFactory {
    private static final int START_NORMAL_FRAME_NUMBER = 1;
    private static final int END_NORMAL_FRAME_NUMBER = 9;

    public Frame create() {
        Frame frame = FinalFrame.create();

        for (int i = END_NORMAL_FRAME_NUMBER; i >= START_NORMAL_FRAME_NUMBER; i--) {
            Frame prevFrame = NormalFrame.create(FrameNumber.from(i));
            prevFrame.setNext(frame);
            frame = prevFrame;
        }
        return frame;
    }
}
