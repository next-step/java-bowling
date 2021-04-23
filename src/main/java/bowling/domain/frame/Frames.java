package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {
    private static final int MAX_NORMAL_FRAME_COUNT = 9;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        frames.add(NormalFrame.init());
        return new Frames(frames);
    }

    public void bowl(int pinCount) {
        if (isDone()) {
            frames.add(nextFrame(currentFrame()));
        }
        currentFrame().bowl(pinCount);
    }

    private Frame currentFrame() {
        return frames.get(frames.size() - 1);
    }

    private Frame nextFrame(Frame frame) {
        if (frames.size() == MAX_NORMAL_FRAME_COUNT)
            return FinalFrame.init();
        return frame.next();
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    // 한 프레임 끝남 여부
    public boolean isDone() {
        return frames.get(frames.size() - 1).isDone();
    }

    // 전체 프레임 끝남 여부
    public boolean isAllDone() {
        return frames.size() == MAX_NORMAL_FRAME_COUNT + 1
            && frames.stream().allMatch(frame -> frame.isDone());
    }
}
