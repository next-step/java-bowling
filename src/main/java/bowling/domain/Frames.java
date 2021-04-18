package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public Frames(Frame frame) {
        this.frames.add(frame);
    }

    public void bowl(Integer pins) {
        if (isNextFrame()) {
            frames.add(new Frame(0, 0));
        }
        currentFrame().bowl(pins);
    }

    public boolean isNextFrame() {
        return currentFrame().isNextFrame();
    }

    public int tryCount() {
        return currentFrame().tryCount();
    }

    protected Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }
}
