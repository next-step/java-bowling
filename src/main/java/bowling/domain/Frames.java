package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private final List<Frame> frames;

    private Frames(List<Frame> values) {
        this.frames = values;
    }

    public static Frames init() {
        return new Frames(new ArrayList<>(Collections.singletonList(NormalFrame.init())));
    }

    public void bowl(Pin pins) {
        Frame frame = getLastFrame();
        Frame result = frame.bowl(pins);

        if (isFrameCreated(frame, result)) {
            frames.add(result);
        }
    }

    private boolean isFrameCreated(Frame lastFrame, Frame resultFrame) {
        return lastFrame.isEnd() && !resultFrame.isEnd();
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public int getLastFrameIndex() {
        return getLastFrame().getFrameIndex();
    }

    public boolean hasNext() {
        return !(getLastFrame().isEnd() && frames.size() == Index.MAX_INDEX);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
