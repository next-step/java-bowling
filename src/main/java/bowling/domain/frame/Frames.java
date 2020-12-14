package bowling.domain.frame;

import bowling.domain.FallingPinCount;
import bowling.domain.state.State;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class Frames {

    private static final int MAX_FRAMES_PER_GAME = 10;

    private final LinkedList<Frame> frames;

    public static Frames of(LinkedList<Frame> frames) {
        return new Frames(frames);
    }

    private Frames(LinkedList<Frame> frames) {
        this.frames = frames;
    }

    public void saveSate(FallingPinCount currentFramePitch) {
        Frame last = this.frames.getLast();
        Frame frame = last.roll(currentFramePitch);
        if (!isFullFrame() && !last.equals(frame)) {
            this.frames.add(frame);
        }
    }

    public Map<Integer, State> stateGroupBy() {
        return frames.stream()
            .collect(Collectors.toMap(Frame::getFrameSequence, Frame::state, (frame1, frame2) -> frame1, LinkedHashMap::new));
    }

    public int getCurrentFrameSequence() {
        return frames.getLast()
            .getFrameSequence();
    }

    public boolean isGameFinished() {
        return isFullFrame() && isAllFrameDone();
    }

    private boolean isFullFrame() {
        return frames.size() == MAX_FRAMES_PER_GAME;
    }

    private boolean isAllFrameDone() {
        return frames.getLast().isFrameFinished();
    }
}
