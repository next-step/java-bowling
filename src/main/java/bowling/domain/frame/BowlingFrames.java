package bowling.domain.frame;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingFrames {

    public static final int LAST_FRAME = 10;

    private final List<Frame> frames;

    public BowlingFrames() {
        this.frames = initFrames();
    }

    public Frame getFrame(int index) {
        return frames.get(index);
    }

    public int getSize() {
        return frames.size();
    }

    private List<Frame> initFrames() {
        List<Frame> frames = IntStream.range(0, LAST_FRAME - 1)
                .mapToObj(i -> new NormalFrame())
                .collect(Collectors.toList());
        frames.add(new FinalFrame());
        return frames;
    }

}
