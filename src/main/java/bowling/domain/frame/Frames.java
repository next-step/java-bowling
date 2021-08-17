package bowling.domain.frame;

import bowling.domain.frame.exception.FramesSizeException;
import java.util.List;

public class Frames {

    private static final int MAX = 10;

    private final List<Frame> frames;

    private Frames(final List<Frame> frames) {
        this.frames = frames;
        framesValidation();
    }

    public static Frames of(final List<Frame> frames) {
        return new Frames(frames);
    }

    public void add(final Frame frame) {
        frames.add(frame);
        framesValidation();
    }

    private void framesValidation() {
        if (frames.size() > MAX) {
            throw new FramesSizeException();
        }
    }
}
