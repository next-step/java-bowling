package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

public class Frames {
    private static final int GAME_FRAME_SIZE = 10;
    private static final int ONE_FRAME_SIZE = 1;
    private static final int LAST_FRAME_INDEX = 9;
    private static final int FIRST_FRAME_INDEX = 0;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames createFrames() {
        return new Frames(new ArrayList<>());
    }

    public void add(Frame frame) {
        frames.add(frame);
    }

    public void addNextFrame() {
        if (isFirst()) {
            frames.add(DefaultFrame.firstFrame());
            return;
        }

        DefaultFrame currentFrame = (DefaultFrame) findLastFrame();
        if (isLast()) {
            frames.add(currentFrame.lastFrame(lastIndex()));
            return;
        }
        frames.add(currentFrame.nextFrame(lastIndex()));
    }

    public Frame findLastFrame() {
        return frames.get(lastIndex());
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    private int lastIndex() {
        return frames.size() - ONE_FRAME_SIZE;
    }

    public int size() {
        return frames.size();
    }

    public boolean isOver() {
        return frames.size() == GAME_FRAME_SIZE;
    }

    private boolean isLast() {
        return nonNull(frames) && frames.size() == LAST_FRAME_INDEX;
    }

    private boolean isFirst() {
        return nonNull(frames) && frames.size() == FIRST_FRAME_INDEX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames that = (Frames) o;
        return Objects.equals(frames, that.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
