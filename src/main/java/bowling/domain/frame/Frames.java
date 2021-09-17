package bowling.domain.frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Frames {

    private static final int START_FRAME_ROUND = 1;
    private static final int FINAL_FRAME_ROUND = 10;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        List<Frame> frames = new ArrayList<>();
        Frame frame = NormalFrame.createFirstFrame();
        frames.add(frame);
        IntStream.range(START_FRAME_ROUND, FINAL_FRAME_ROUND)
            .mapToObj(index -> frame.createNextFrame())
            .forEach(frames::add);
        return new Frames(frames);
    }

    public List<Frame> values() {
        return Collections.unmodifiableList(frames);
    }

}
