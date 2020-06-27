package bowling.domain;

import java.util.LinkedList;
import java.util.List;

public class Frames {
    private static final int LAST_FRAME = 10;

    private List<Frame> frames;
    private int frameIndex;

    private Frames(List<Frame> frames, int frameIndex) {
        this.frames = frames;
        this.frameIndex = frameIndex;
    }

    public static Frames init() {
        List<Frame> frames = new LinkedList<>();
        frames.add(NormalFrame.newInstance());
        return new Frames(frames, 0);
    }

    public Frames bowling(int downPin) {
        Frame frame = frames.get(frameIndex).bowl(downPin);
        updateFrame(frame);
        return this;
    }

    public void next() {
        if (frames.get(frameIndex).isLastTrying()) {
            frames.add(createNextFrame());
            frameIndex++;
        }
    }

    public boolean isLast() {
        return frameIndex == LAST_FRAME;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public String getResult(int index){
        return frames.get(index).getResult();
    }

    public int size() {
        return frames.size();
    }

    private void updateFrame(Frame frame) {
        frames.remove(frameIndex);
        frames.add(frame);
    }

    private Frame createNextFrame() {
        if (isBeforeLastIndex()) {
            return FinalFrame.newInstance();
        }
        return NormalFrame.newInstance();
    }

    private boolean isBeforeLastIndex() {
        return frameIndex == 8;
    }


}
