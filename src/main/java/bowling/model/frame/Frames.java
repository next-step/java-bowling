package bowling.model.frame;

import bowling.model.Pins;
import bowling.utility.Assert;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class Frames {

    private final LinkedList<Frame> frames;

    private Frames(List<Frame> frames) {
        Assert.notEmpty(frames, "frames must not empty");
        this.frames = new LinkedList<>(frames);
    }

    public static Frames init() {
        return new Frames(Collections.singletonList(NormalFrame.init(FrameNumber.FIRST)));
    }

    static Frames from(List<Frame> frames) {
        return new Frames(frames);
    }

    public boolean isFinished() {
        return frames.getLast().isFinal() && frames.getLast().isEnd();
    }

    public Frames addedFrames(Pins countOfHit) {
        validateStateToAdd();
        LinkedList<Frame> newFrames = newFramesWithoutNotEndedFrame();
        newFrames.add(this.frames.getLast().next(countOfHit));
        return from(newFrames);
    }

    public FrameNumber nextFrameNumber() {
        Frame last = this.frames.getLast();
        if (last.isEnd()) {
            return last.number().increase();
        }
        return last.number();
    }

    public List<Frame> list() {
        return Collections.unmodifiableList(this.frames);
    }

    private LinkedList<Frame> newFramesWithoutNotEndedFrame() {
        LinkedList<Frame> newFrames = new LinkedList<>(this.frames);
        Frame last = newFrames.getLast();
        if (!last.isEnd()) {
            newFrames.removeLast();
        }
        return newFrames;
    }

    private void validateStateToAdd() {
        if (isFinished()) {
            throw new IllegalStateException(String.format("frames(%s) is already finished", this));
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public String toString() {
        return "Frames{" +
                "frames=" + frames +
                '}';
    }
}
