package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;
import static java.util.Objects.isNull;

public class Frames {
    public static final int MAX_FRAME_NUMBER = 10;

    private final List<Frame> frames;

    Frames(List<Frame> frames) {
        if (isNull(frames) || frames.isEmpty()) {
            throw new IllegalArgumentException("frames 가 null 이거나 비어있을 수 없습니다.");
        }
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(List.of(Frame.init()));
    }

    public Frames bowl(Score score) {
        Frame frame = lastFrame().bowl(score);
        return update(lastIndex(), frame);
    }

    public Frames next() {
        if (!lastFrame().isFinished()) {
            return this;
        }

        if (lastFrameNumber() == MAX_FRAME_NUMBER - 1) {
            return add(FinalFrame.init());
        }

        return add(Frame.init());
    }

    public boolean isOver() {
        return lastFrameNumber() > MAX_FRAME_NUMBER;
    }

    public int lastFrameNumber() {
        return lastIndex() + 1;
    }

    public List<Frame> getFrames() {
        return unmodifiableList(frames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frames)) return false;

        Frames frames1 = (Frames) o;

        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return frames != null ? frames.hashCode() : 0;
    }

    private Frames add(Frame frame) {
        List<Frame> result = new ArrayList<>(frames);
        result.add(frame);
        return new Frames(result);
    }

    private Frames update(int frameIndex, Frame frame) {
        List<Frame> result = new ArrayList<>(frames);
        result.set(frameIndex, frame);
        return new Frames(result);
    }

    private Frame lastFrame() {
        return frames.get(lastIndex());
    }

    private int lastIndex() {
        return frames.size() - 1;
    }
}
