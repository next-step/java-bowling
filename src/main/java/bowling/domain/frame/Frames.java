package bowling.domain.frame;

import bowling.domain.frame.exception.FramesSizeException;
import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int MAX = 10;

    private final List<Frame> frames;

    private Frames(final List<Frame> frames) {
        this.frames = frames;
    }

    private void framesValidation() {
        if (frames.size() > MAX - 1) {
            throw new FramesSizeException();
        }
    }

    public static Frames of() {
        return new Frames(new ArrayList<>());
    }

    public void add(final Frame frame) {
        framesValidation();
        frames.add(frame);
    }

    public int size() {
        return frames.size();
    }
}
