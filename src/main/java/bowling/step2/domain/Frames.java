package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Frames {
    private static final int LAST_FRAME = 10;
    private final List<Frame> frames;

    private Frames (List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of (List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init () {
        return IntStream.rangeClosed(1, LAST_FRAME)
                        .mapToObj(Frame::init)
                        .collect(collectingAndThen(toList(), Frames::of));
    }
}