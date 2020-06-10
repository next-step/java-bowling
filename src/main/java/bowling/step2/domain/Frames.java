package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {
    private static final int LAST_FRAME = 10;

    private final List<Frame> frames;

    private Frames (List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of (List<Frame> frames) {
        return new Frames(new ArrayList<>());
    }

    public void next (Frame frame) {
        frames.add(frame.next());
    }
}