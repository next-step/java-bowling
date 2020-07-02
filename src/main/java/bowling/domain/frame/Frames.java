package bowling.domain.frame;

import bowling.domain.status.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    public static final int MAX_FRAME_INDEX = 10;

    private final LinkedList<Frame> frames;
    private int index;

    public Frames(LinkedList<Frame> frames, int index) {
        this.frames = frames;
        this.index = index;
    }

    public static Frames init() {
        LinkedList<Frame> frames = new LinkedList<>();
        frames.add(NormalFrame.init());

        return new Frames(frames, 0);
    }

    public Status bowl(int downPin) {
        return frames.get(index).bowl(downPin);
    }

    public void next() {
        Frame currentFrame = frames.getLast();
        if (!currentFrame.canPlayMore()) {
            index++;
            addNextFrame(currentFrame);
        }
    }

    public int getIndex() {
        return index;
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public boolean isLastFrame() {
        return index == MAX_FRAME_INDEX;
    }

    private void addNextFrame(Frame currentFrame) {
        if (index < MAX_FRAME_INDEX) {
            frames.add(currentFrame.nextFrame(index));
        }
    }
}
