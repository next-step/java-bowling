package bowling.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-16 오전 8:58
 * Developer : Seo
 */
public class Frames {
    private final List<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
        Frame.init();
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
