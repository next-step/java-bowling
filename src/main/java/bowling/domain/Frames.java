package bowling.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-16 오전 8:58
 * Developer : Seo
 */
public class Frames {
    public static final int PREVIOUS_NO = 1;

    private final List<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
        frames.add(Frame.init());
    }

    public void next(int frameNo) {
        Frame frame = frames.get(frameNo - PREVIOUS_NO);
        Frame next = frame.next(frameNo);
        frames.add(next);
    }

    public int size() {
        return frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public Frame get(int i) {
        return frames.get(i);
    }
}
