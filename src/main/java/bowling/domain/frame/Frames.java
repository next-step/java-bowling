package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    public static final int MAX_FRAMES_SIZE = 10;

    private final List<Frame> frames = new ArrayList<>();

    public Frames(int pinNo) {
        frames.add(NormalFrame.init(pinNo));
    }

    public void addPin(int pinNo) {
        if (isFinished()) {
            throw new IllegalStateException("can't add more pins");
        }

        Frame lastFrame = getLastFrame();
        if (lastFrame.isFull()) {
            frames.add(lastFrame.nextFrame(pinNo));
            return;
        }
        lastFrame.addPin(pinNo);
    }

    public boolean isFinished() {
        return frames.size() == MAX_FRAMES_SIZE && getLastFrame().isFull();
    }

    private Frame getLastFrame() {
        return frames.get(frames.size() - 1);
    }

    public int currentFrame() {
        return getLastFrame().isFull()
                ? frames.size() + 1
                : frames.size();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
