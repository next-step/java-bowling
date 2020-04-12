package bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BowlingFrames {
    private final List<BowlingFrame> frames;

    public BowlingFrames() {
        frames = new ArrayList<>();
    }

    private BowlingFrames(final List<BowlingFrame> frames) {
        this.frames = Collections.unmodifiableList(frames);
    }

    public int size() {
        return frames.size();
    }
}
