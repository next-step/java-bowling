package domain.frame;

import java.util.ArrayList;
import java.util.List;

import static domain.frame.FrameIndex.TOTAL_FRAME;

public class FrameFactory {

    private final static int FIRST = 0;

    public static Frames createFrames() {
        Frame before = new LastFrame();

        List<Frame> frames = new ArrayList<>();
        frames.add(FIRST, before);

        for (int i = 0; i < TOTAL_FRAME; i++) {
            before = new NormalFrame(before);
            frames.add(FIRST, before);
        }
        return Frames.of(frames);
    }
}
