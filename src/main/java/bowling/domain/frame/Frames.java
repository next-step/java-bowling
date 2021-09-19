package bowling.domain.frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frames {

    private static final int FIRST_TRIAL = 1;

    private static final int MAX_SIZE = 10;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames create() {
        return new Frames(new ArrayList<>());
    }

    public List<Frame> getAll() {
        return frames;
    }

    public int nextTryFrame() {
        if (frames.isEmpty()) {
            return FIRST_TRIAL;
        }

        Frame frame = frames.get(frames.size() - 1);
        return frame.next();
    }

    public Frames execute(int score) {

        if (frames.isEmpty()) {
            frames.add(NormalFrame.start(score));
            return this;
        }

        Frame frame = frames.get(frames.size() - 1);

        if (frame.isLast()) {
            frames.add(FinalFrame.start(score));
            return this;
        }

        frame = frame.tryNext(score);

        if (frame.isNowFirstTry()) {
            frames.add(frame);
            return this;
        }

        return this;
    }

    public boolean isLast() {
        if (frames.isEmpty()) return false;

        int size = frames.size();
        Frame frame = frames.get(size - 1);

        return size == MAX_SIZE && frame.isLast();
    }

    public int size() {
        return frames.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames = (Frames) o;
        return Objects.equals(this.frames, frames.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
