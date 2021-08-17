package bowling.domain.frame;

import bowling.domain.frame.exception.FramesSizeException;
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
        return frames.size() == MAX;
    }

    public int size() {
        return frames.size();
    }

    public void pitch(final PitchResult result) {
        final Frame frame = frames.get(frames.size() - 1);

        if (frame.isEnd()) {
            final Frame newFrame = Frame.of();
            newFrame.pitch(result);
            frames.add(newFrame);
        }

        frame.pitch(result);
    }
}
