package bowling;

import bowling.frame.Frame;
import bowling.frame.FrameType;

import java.util.LinkedList;
import java.util.List;

public class Frames {
    public static final int FRAME_COUNT = 10;
    private static final String FRAME_COUNT_EXCEPTION = "프레임은 10번을 넘을 수 없습니다";
    private List<Frame> frames;

    public Frames() {
        this.frames = new LinkedList<>();
    }

    public void addFrame(Frame frame) {
        if (this.frames.size() >= FRAME_COUNT) {
            throw new IllegalArgumentException(FRAME_COUNT_EXCEPTION);
        }
        if (this.frames.size() != 0
                && this.frames.get(frames.size() - 1).getFrameType() == FrameType.FINAL) {
            throw new IllegalArgumentException(FRAME_COUNT_EXCEPTION);
        }
        this.frames.add(frame);
    }

    public List<Frame> getFrames() {
        return this.frames;
    }
}