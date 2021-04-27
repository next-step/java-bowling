package bowling.domain.engine.frame;

import bowling.domain.concrete.frame.FinalFrame;
import bowling.domain.concrete.frame.NormalFrame;

import java.util.LinkedList;

public class FrameCreator {

    private static final int MAX_FRAMES = 10;

    private FrameCreator() {}

    @SuppressWarnings("java:S1319")
    public static LinkedList<Frame> createFrames() {
        LinkedList<Frame> frames = new LinkedList<>();
        Frame lastCreatedFrame = FinalFrame.init();
        frames.add(lastCreatedFrame);


        for (int i = 0; i < MAX_FRAMES - 1; i++) {
            lastCreatedFrame = NormalFrame.init(lastCreatedFrame);
            frames.addFirst(lastCreatedFrame);
        }

        return frames;
    }

}
