package bowling.domain;


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

        if (currentFrame.rollingEnd() && !currentFrame.isEndAllFrame()) {
            frames.add(currentFrame.next());
        }
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
