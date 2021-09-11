package bowling.domain;

import java.util.LinkedList;
import java.util.Objects;

public class Frames {
    private LinkedList<Frame> frames;

    private Frames() {
        frames = new LinkedList<>();
        Frame frame = new NormalFrame(1);
        frames.addFirst(frame);
    }

    public static Frames newInstance() {
        return new Frames();
    }

    public Frame get(int frameNumber) {
        return frames.get(frameNumber);
    }

    public int size() {
        return frames.size();
    }

    public void add(Frame frame) {
        if (isNextFrame(frame)) {
            frames.add(frame);
            return;
        }
        frames.remove(frames.size() - 1);
        frames.addLast(frame);
    }

    private boolean isNextFrame(Frame frame) {
        Frame prevFrame = frames.getLast();
        return !prevFrame.equals(frame);
    }

    public Frame currentFrame() {
        return frames.getLast();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }

}
