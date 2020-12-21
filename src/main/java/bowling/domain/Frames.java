package bowling.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-16 오전 8:58
 * Developer : Seo
 */
public class Frames {
    private final List<Frame> frames;

    public Frames(Users users) {
        this.frames = new LinkedList<>();
        frames.add(Frame.init(users));
    }

    public void bowl(int frameNo) {
        Frame frame = frames.get(frameNo - 1);
        frames.add(frame.bowl(frameNo));
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
