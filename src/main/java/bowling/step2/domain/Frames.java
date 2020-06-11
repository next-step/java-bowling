package bowling.step2.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class Frames {
    private static final int FIRST_FRAME = 1;
    private static final int LAST_FRAME = 10;

    private final List<Frame> frames;

    private Frames (List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of (List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init () {
        return new Frames(new ArrayList<>());
    }

    public Stream<Frame> stream () {
        return frames.stream();
    }

    public List<Frame> preview () {
        return IntStream.range(0, LAST_FRAME)
                        .mapToObj(index -> Optional.of(frames.get(index))
                                                   .orElse(null))
                        .collect(toList());
    }
}