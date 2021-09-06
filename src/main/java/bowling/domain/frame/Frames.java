package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private static final int LIMIT_SIZE = 10;
    private static final int NORMAL_FRAME_SIZE = 9;

    private final List<Frame> frames = new ArrayList<>();

    public void add(Frame frame) {
        if (frames.size() == LIMIT_SIZE) {
            throw new IllegalStateException();
        }
        frames.add(frame);
    }

    public List<Frame> list() {
        return Collections.unmodifiableList(frames);
    }

    public Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public void roll(int numberOfFrame, int fallenPin) {
        frames.set(numberOfFrame - 1, currentFrame().roll(fallenPin));
    }

    public boolean isEnd() {
        return frames.size() == LIMIT_SIZE && frames.get(NORMAL_FRAME_SIZE).isEnd();
    }
}
