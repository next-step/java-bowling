package bowling.domain;

import java.util.List;

public class Frames {
    List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init(List<Frame> frames) {
        return new Frames(frames);
    }

    public int size() {
        return frames.size();
    }

}
