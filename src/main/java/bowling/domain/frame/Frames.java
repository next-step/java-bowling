package bowling.domain.frame;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frames {

    private static final int MAX = 10;

    private final List<Frame> frames;

    private Frames(final List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of() {
        return new Frames(Stream.of(NormalFrame.of())
            .collect(Collectors.toList()));
    }

    public boolean isEnd() {
        return frames.size() == MAX && current().isEnd();
    }

    public Frame current() {
        return frames.get(frames.size() - 1);
    }

    public int currentIndex() {
        if (frames.get(frames.size() - 1).isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public void pitch(final PitchResult result) {
        final Frame current = current();

        if (current.isEnd()) {
            final NormalFrame newFrame = NormalFrame.of();
            newFrame.pitch(result);
            frames.add(newFrame);
            return;
        }

        if (current.isEnd() && frames.size() == MAX - 1) {
            final LastFrame newFrame = LastFrame.of();
            newFrame.pitch(result);
            frames.add(newFrame);
            return;
        }

        current.pitch(result);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
