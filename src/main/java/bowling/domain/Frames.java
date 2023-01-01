package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    public static final int MAX_FRAME_NUMBER = 10;

    private final List<Frame> frames;

    public Frames() {
        this(initFrames());
    }

    public Frames(List<Frame> frames) {
        this.frames = frames;
    }

    private static List<Frame> initFrames() {
        List<Frame> frames = new ArrayList<>();
        frames.add(new NormalFrame(1));
        return frames;
    }

    public int currentFrameNumber() {
        return currentFrame().frameNumber();
    }

    public void bowl(Pin pin) {
        currentFrame().bowl(pin);
        nextFrame();
    }

    private void nextFrame() {
        if (frames.size() == MAX_FRAME_NUMBER) {
            return;
        }
        if (currentFrame().isFinished()) {
            frames.add(currentFrame().nextFrame());
        }
    }

    public boolean gameFinished() {
        if (frames.size() < MAX_FRAME_NUMBER) {
            return false;
        }
        return currentFrame().isFinished();
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    public List<Frame> results() {
        return Collections.unmodifiableList(frames);
    }

}
