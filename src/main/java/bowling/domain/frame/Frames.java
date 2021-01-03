package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-16 오전 8:58
 * Developer : Seo
 */
public class Frames {
    private final LinkedList<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
        this.frames.add(new NormalFrame());
    }

    public void add(Frame frame) {
        this.frames.add(frame);
    }

    public Frame get() {
        return frames.getLast();
    }

    public List<Frame> getFrames() {
        return this.frames;
    }

    public int size() {
        return this.frames.size();
    }
}
