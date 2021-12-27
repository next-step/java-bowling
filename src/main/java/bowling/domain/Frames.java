package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(new ArrayList<>(Collections.singletonList(NormalFrame.init())));
    }

    public void bowl(Pin pin) {
        Frame last = getLastFrame();
        Frame next = last.bowl(pin);
        if (isFrameCreated(last, next)) {
            frames.add(next);
        }
    }

    private boolean isFrameCreated(Frame first, Frame second) {
        return first.getFrameIndex() != second.getFrameIndex();
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public int getLastFrameIndex() {
        return getLastFrame().getFrameIndex();
    }

    public boolean hasNext() {
        return !(getLastFrame().isEnd() && frames.size() == FrameIndex.MAX);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
