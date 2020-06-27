package bowling.domain;

import java.util.LinkedList;
import java.util.List;

public class Frames {
    public static final int LAST_FRAME = 10;

    List<Frame> frames;
    int frameIndex;

    private Frames(List<Frame> frames, int frameIndex) {
        this.frames = frames;
        this.frameIndex = frameIndex;
    }

    public static Frames init() {
        List<Frame> frames = new LinkedList<>();
        frames.add(Frame.newInstance());
        return new Frames(frames, 0);
    }

    public int size() {
        return frames.size();
    }

    public boolean isLast() {
        return frameIndex == LAST_FRAME;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public Frames bowling(int downPin) {
        Frame frame = frames.get(frameIndex).bowl(downPin);
        update(frame);
        return this;
    }

    private void update(Frame frame) {
        frames.remove(frameIndex);
        frames.add(frame);
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public void next() {
        if (frames.get(frameIndex).isLast(2)) {
            frames.add(Frame.newInstance());
            frameIndex++;
        }
    }
}
