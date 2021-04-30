package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import bowling.domain.state.BowlingPin;

public class Frames {
    private static final int MAX_NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.init());
        return new Frames(frames);
    }

    public void bowl(int pinCount) {
        this.currentFrame().bowl(BowlingPin.of(pinCount));
    }

    public void next() {
        if (this.currentFrame().isDone()
            && this.frames.size() <= MAX_NORMAL_FRAME_COUNT) {
            frames.add(this.nextFrame());
        }
    }

    public boolean isDone() {
        return this.currentFrame().isDone();
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    private Frame nextFrame() {
        return this.currentFrame().next(frames.size());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Frames frames1 = (Frames)o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
