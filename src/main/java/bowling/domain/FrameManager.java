package bowling.domain;

import java.util.LinkedList;

public class FrameManager {
    private static final int BOWLING_FRAME_COUNT = 10;

    public static Frames makeDefaultFrames(Player player) {
        LinkedList<Frame> frames = new LinkedList<>();

        NormalFrame normalFrame = NormalFrame.create();
        for (int i = 0; i < BOWLING_FRAME_COUNT - 1; i++) {
            frames.add(normalFrame);
            normalFrame = NormalFrame.from(normalFrame);
        }
        FinalFrame finalFrame = FinalFrame.from(normalFrame);
        frames.add(finalFrame);

        return Frames.getDefault(player, frames);
    }

    public static void process(Frames defaultFrames) {
//        InputView.scanFrameBowl();
    }
}
