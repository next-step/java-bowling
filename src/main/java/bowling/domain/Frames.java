package bowling.domain;

import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames(final List<Frame> frames) {

        this.frames = frames;
    }

    public static Frames init() {

        final List<Frame> frames = List.of(NormalFrame.ready());
        return new Frames(frames);
    }

    public void addFrame() {

        frames.add(lastFrame().nextFrame());
    }

    public Frame lastFrame() {

        return frames.get(frames.size() - 1);
    }

    public boolean isLast() {

        return !lastFrame().canBowl();
    }

    public List<Frame> getFrames() {

        return Collections.unmodifiableList(frames);
    }
}
