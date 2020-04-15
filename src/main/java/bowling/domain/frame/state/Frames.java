package bowling.domain.frame.state;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.Arrays;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    private Frames(final List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames ofFirst() {
        return new Frames(Arrays.asList(NormalFrame.ofFirst()));
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
