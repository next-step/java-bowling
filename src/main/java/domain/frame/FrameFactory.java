package domain.frame;

import java.util.ArrayList;
import java.util.List;

import static domain.frame.FrameIndex.LAST_NORMAL_FRAME;
import static domain.frame.FrameIndex.START_FRAME;

public class FrameFactory {

    private final static int FIRST = 0;

    public static Frames createFrames() {
        List<Frame> frames = new ArrayList<>();

        Frame before = new LastFrame();
        frames.add(FIRST, before);

        createNormalFrames(before, frames);
        return Frames.of(frames);
    }

    private static void createNormalFrames(Frame before, List<Frame> frames) {
        for (int i = START_FRAME; i < LAST_NORMAL_FRAME; i++) {
            before = new NormalFrame(before);
            frames.add(FIRST, before);
        }
    }
}
