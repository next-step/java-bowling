package bowling.domain.frame;


import bowling.domain.Pin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    private final List<Frame> frames = new ArrayList<>();

    public Frames() {
        this.frames.add(new NormalFrame(Frame.MIN_FRAME_INDEX));
    }

    public void play(Pin pin) {
        Frame currentFrame = getLastFrame();
        currentFrame.bowl(pin);

        if (isMaxFrameSize() && currentFrame.rollingEnd() && !currentFrame.isEndAllFrame()) {
            frames.add(currentFrame.next());
        }
    }

    private boolean isMaxFrameSize() {
        return frames.size() < 10;
    }

    public String currentFrameIndex() {
        return String.valueOf(getLastFrame().currentFrameIndex());
    }

    public boolean isEndAllFrame() {
        return getLastFrame().isEndAllFrame();
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int size() {
        return frames.size();
    }
}
