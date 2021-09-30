package bowling.domain.frame;

import static bowling.domain.frame.AbstractFrame.FINAL_ROUND;

import bowling.domain.score.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Frames {

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames createFramesByFirstFrame(Frame frame) {
        List<Frame> frames = new ArrayList<>();
        frames.add(frame);
        while (frame.round() < FINAL_ROUND && Objects.nonNull(frame.nextFrame())) {
            frame = frame.nextFrame();
            frames.add(frame);
        }
        return new Frames(frames);
    }

    public int size() {
        return frames.size();
    }

    public Stream<Score> scores() {
        return frames.stream()
            .map(Frame::score);
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
