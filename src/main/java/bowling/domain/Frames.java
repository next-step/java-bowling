package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public Frames(Frame frame) {
        this.frames.add(frame);
    }

    public void bowl(Integer pins) {
        if(nextFrame()) {
            frames.add(new Frame(0, 0));
        }
        currentFrame().bowl(pins);

    }

    private boolean nextFrame() {
        return currentFrame().tryCount() > 1;
    }

    protected Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public boolean isNextFrame() {
        return currentFrame().nextFrame();
    }

    public int tryCount() {
        return currentFrame().tryCount();
    }
}
