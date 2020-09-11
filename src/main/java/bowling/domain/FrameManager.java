package bowling.domain;

import java.util.LinkedList;

public class FrameManager {
    private static final int BOWLING_FRAME_COUNT = 10;

    public static Frames makeDefaultFrames() {
        LinkedList<Frame> frames = new LinkedList<>();

        NormalFrame normalFrame = NormalFrame.create();
        for (int i = 0; i < BOWLING_FRAME_COUNT - 2; i++) {
            frames.add(normalFrame);
            normalFrame = NormalFrame.from(normalFrame);
        }
        FinalFrame finalFrame = FinalFrame.from(normalFrame);
        frames.add(finalFrame);

        return Frames.getDefault(frames);
    }

}
