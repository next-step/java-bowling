package bowling.domain.frame;

import bowling.domain.frame.exception.FramesSizeException;
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

    private void framesValidation() {
        if (frames.size() > MAX - 1) {
            throw new FramesSizeException();
        }
    }

    public static Frames of() {
        return new Frames(Stream.of(Frame.of()).collect(Collectors.toList()));
    }

    public boolean isEnd() {
        return frames.size() == MAX && frames.get(frames.size() - 1).isEnd();
    }

    public int size() {
        return frames.size();
    }

    public int current() {
        if (frames.get(frames.size() - 1).isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public void pitch(final PitchResult result) {
        final Frame frame = frames.get(frames.size() - 1);

        if (frame.isEnd()) {
            final Frame newFrame = Frame.of();
            newFrame.pitch(result);
            frames.add(newFrame);
            return;
        }

        frame.pitch(result);
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }
}
