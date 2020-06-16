package bowling.step2.domain;

import bowling.step2.domain.frame.Frame;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Frames {
    public static final int LAST_FRAME = 10;

    private final List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    public static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames init() {
        return of(new ArrayList<>());
    }

    public static Frames ofLastFrame(Frame nowFrame) {
        List<Frame> frames = new ArrayList<>();
        Frame temp = nowFrame;
        do {
            frames.add(temp);
            temp = temp.getPrevFrame();
        } while (temp != null);
        Collections.reverse(frames);
        return new Frames(frames);
    }

    public Stream<Frame> stream() {
        return frames.stream();
    }

    public Stream<Frame> preview() {
        int size = frames.size();
        return IntStream.range(0, Frames.LAST_FRAME)
                        .mapToObj(index -> index < size ? frames.get(index) : null);
    }
}