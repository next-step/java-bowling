package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames;

    private Frames(final List<Frame> frames) {

        this.frames = frames;
    }

    public static Frames init() {

        return new Frames(new ArrayList<>(List.of(NormalFrame.ready())));
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
