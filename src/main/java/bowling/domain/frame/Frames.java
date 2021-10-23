package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Frames {

    private static final int MAX_SIZE = 10;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames create() {
        return new Frames(new ArrayList<>());
    }

    public List<Frame> getAll() {
        return Collections.unmodifiableList(frames);
    }

    public Frames execute(int score) {

        if (frames.isEmpty()) {
            frames.add(NormalFrame.start(score));
            return this;
        }

        Frame frame = frames.get(frames.size() - 1);
        frame = frame.bowl(score);

        if (frame.isNowFirstTry()) {
            frames.add(frame);
        }
        return this;
    }

    public Frames calculateScores() {
        frames.forEach(Frame::calculateScore);
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

    public boolean nowFrameEnd() {

        int size = frames.size();
        Frame frame = frames.get(size - 1);

        return frame.tryAll();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
