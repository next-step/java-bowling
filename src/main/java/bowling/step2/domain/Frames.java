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

    public static Frames init (List<Frame> frames, Players players) {
        return IntStream.range(0, LAST_FRAME)
                        .mapToObj(frame -> Frame.init(frame, players))
                        .collect(collectingAndThen(toList(), Frames::new));
    }
}