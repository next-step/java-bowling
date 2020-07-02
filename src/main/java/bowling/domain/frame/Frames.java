package bowling.domain.frame;

import bowling.domain.status.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    public static final int MAX_FRAME_INDEX = 10;
    private final List<Frame> frames;
    private int index;

    public Frames(List<Frame> frames, int index) {
        this.frames = frames;
        this.index = index;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.init());

        return new Frames(frames, 0);
    }

    public Status bowl(int downPin) {
        return frames.get(index).bowl(downPin);
    }

    public void next() {
        Frame currentFrame = frames.get(index);
        if (!currentFrame.canPlayMore()) {
            index++;
            addNextFrame(currentFrame);
        }
    }

    private void addNextFrame(Frame currentFrame) {
        if (index < MAX_FRAME_INDEX) {
            frames.add(currentFrame.nextFrame(index));
        }
    }

    public int getIndex() {
        return index;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public int size() {
        return frames.size();
    }
}
