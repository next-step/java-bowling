package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int COUNT_OF_FRAMES = 10;

    private final List<Frame> frames;

    private Frames() {
        frames = new ArrayList<>();

        for (int i = 1; i < COUNT_OF_FRAMES; i++) {
            frames.add(new NormalFrame());
        }

        frames.add(new FinalFrame());
    }

    public static Frames init() {
        return new Frames();
    }

    public int size() {
        return frames.size();
    }
}
