package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    public Frames(List<Frame> frames) {

        this.frames = frames;
    }

    public static Frames init() {

        List<Frame> frames = new ArrayList<>();
        frames.add(new NormalFrame());

        return new Frames(frames);
    }

    public void addFrame() {

        frames.add(lastFrame().nextFrame());
    }

    public Frame lastFrame() {

        return frames.get(frames.size() - 1);
    }

    public boolean end() {

        return lastFrame().canPitch();
    }

    public List<Frame> getFrames() {

        return Collections.unmodifiableList(frames);
    }
}
