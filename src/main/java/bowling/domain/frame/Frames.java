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
        return frames.size() == MAX && getLast().isEnd();
    }

    public int current() {
        if (getLast().isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public Frame getLast() {
        return frames.get(frames.size() - 1);
    }

    public void pitch(final PitchResult result) {
        final Frame lastFrame = getLast();

        if (lastFrame.isEnd()) {
            final NormalFrame newNormalFrame = NormalFrame.of();
            newNormalFrame.pitch(result);
            frames.add(newNormalFrame);
            return;
        }

        lastFrame.pitch(result);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
