package bowling.frame;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames initiate() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.initiate());
        return new Frames(frames);
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
