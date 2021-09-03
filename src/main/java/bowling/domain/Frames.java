package bowling.domain;

import java.util.LinkedList;
import java.util.Objects;

public class Frames {
    private LinkedList<Frame> frames;

    public Frames(){
        frames = new LinkedList<>();
    }
    private Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of(LinkedList<Frame> frames) {
        return new Frames(frames);
    }

    public Frame get(int frameNumber) {
        return frames.get(frameNumber);
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public boolean isFinish() {
        return frames.getLast().isFinish();
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
