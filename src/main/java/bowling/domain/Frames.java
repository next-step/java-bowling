package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static final int LAST_FRAME = 10;
    private List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frames() {
        this.frames = new ArrayList<>();
        for (int i = 0; i < LAST_FRAME; i++) {
            Frame frame = new Frame(i + 1);
            frames.add(frame);
        }
    }

    public Frame frameByIndex(int index) {
        return frames.get(index);
    }

    public List<Frame> unmodifiableFrames() {
        return Collections.unmodifiableList(frames);
    }
}
