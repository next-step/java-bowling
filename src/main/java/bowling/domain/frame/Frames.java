package bowling.domain.frame;

import java.util.LinkedList;
import java.util.Objects;

public class Frames {

    private final LinkedList<Frame> frames;

    private Frames(LinkedList<Frame> frames) {
        this.frames = new LinkedList<>(frames);
    }

    public static Frames readyFrames() {
        LinkedList<Frame> frames = new LinkedList<>();
        frames.add(NormalFrame.readyFrame(Round.FIRST));
        return from(frames);
    }

    public static Frames from(LinkedList<Frame> frames) {
        return new Frames(frames);
    }

    public boolean isGameEnd() {
        return frames.getLast().isGameEnd();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Frames frames1 = (Frames) o;
        return Objects.equals(frames, frames1.frames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frames);
    }
}
