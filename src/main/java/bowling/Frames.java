package bowling;

import java.util.*;

public class Frames {
    private List<Frame> frames;

    Frames() {
        this.frames = new ArrayList<>();
        frames.add(new Frame());
    }

    Frames(int frameNo) {
        this.frames = new ArrayList<>();
        frames.add(new Frame(frameNo));
    }

    public Frames next(int number) {
        Frame current = lastFrame();
        Frame next = current.next(number);
        if (current != next) {
            frames.add(next);
        }
        return this;
    }

    public int currentFrameNo() {
        return lastFrame().getFrameNo();
    }

    private Frame lastFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> getFrames() {
        return frames;
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
