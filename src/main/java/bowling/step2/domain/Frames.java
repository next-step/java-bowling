package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;


public class Frames {

    public static final int FIRST_FRAME = 1;
    public static final int LAST_FRAME = 10;

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

    public static Frames ofFirst (Frame firstFrame) {
        List<Frame> frames = new ArrayList<>();
        Frame temp = firstFrame;
        do {
            frames.add(temp);
            temp = temp.getNextFrame();
        } while (temp.hasNext());
        return new Frames(frames);
    }

    public Stream<Frame> stream () {
        return frames.stream();
    }

    public List<Frame> preview () {
        return IntStream.range(0, Frames.LAST_FRAME)
                        .mapToObj(index -> Optional.of(frames.get(index))
                                                   .orElse(null))
                        .collect(toList());
    }
}