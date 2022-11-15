package bowling.domain.frame;

import bowling.domain.pin.FallenPin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

public class Frames {
    private final List<Frame> frames;

    Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames init() {
        return new Frames(List.of(NormalFrame.init(1)));
    }

    public Frames bowl(FallenPin fallenPin) {
        Frame frame = lastFrame().bowl(fallenPin);
        if (frame.isReady()) {
            return add(frame);
        }

        return update(lastIndex(), frame);
    }

    public boolean isOver() {
        return lastFrame().isFinished();
    }

    public boolean isCurrentFrameFinished() {
        return lastFrame().isReady() || isOver();
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
}
