package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int LIMIT_SIZE_OF_FRAMES = 10;

    private final List<Frame> frames;

    private Frames() {
        frames = new ArrayList<>(LIMIT_SIZE_OF_FRAMES);
    }

    public static Frames of() {
        return new Frames();
    }

    public boolean isBowlingFinish() {
        return currentFrame().isBowlingFinish();
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

}
