package bowling.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Frames implements Iterable<Frame> {

    private final List<Frame> frames;

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public Frames() {
        this.frames = new ArrayList<>();
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public int size() {
        return frames.size();
    }

    public Frame getLastFrame() {
        return frames.get(size() - 1);
    }

    @Override
    public Iterator<Frame> iterator() {
        return frames.iterator();
    }
}
