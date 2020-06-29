package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;

public class Frames {

    private static final int MIN_FRAME_NUMBER = 0;
    private static final int MAX_FRAME_NUMBER = 10;

    private final List<Frame> frames = new LinkedList<>();
    private int frameIndex;

    private Frames(int frameIndex)  {
        validateFrameIndex(frameIndex);
        this.frameIndex = frameIndex;
        frames.add(NormalFrame.create());
    }

    public static Frames create() {
        return new Frames(0);
    }

    public Frames pitch(Point point) {
        Frame frame = frames.get(frameIndex).bowl(point);
        updateFrame(frame);
        return this;
    }

    public void updateFrame(Frame frame) {
        frames.remove(frameIndex);
        frames.add(frame);
    }

    public void next() {
        if(frames.get(frameIndex).isLastPitch()) {
            frames.add(createNextFrame());
            frameIndex++;
        }
    }

    private Frame createNextFrame() {
        if (isBeforeLastIndex()) {
            return FinalFrame.create();
        }
        return NormalFrame.create();
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public boolean isLast() {
        return frameIndex == MAX_FRAME_NUMBER;
    }

    public int getFrameIndex() {
        return frameIndex;
    }

    public int size() {
        return frames.size();
    }

    private boolean isBeforeLastIndex() {
        return frameIndex == 8;
    }

    private void validateFrameIndex(int frameIndex) {
        if (frameIndex < MIN_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 0보다 작을 수 없습니다.");
        }

        if (frameIndex > MAX_FRAME_NUMBER) {
            throw new IllegalArgumentException("프레임은 10보다 클 수 없습니다.");
        }

        if (frameIndex > frames.size()) {
            throw new IllegalArgumentException("index는 frames의 크기보다 클 수 없습니다.");
        }
    }
}
