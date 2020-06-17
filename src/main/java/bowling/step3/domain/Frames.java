package bowling.step3.domain;

import bowling.step3.domain.frame.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Frames {
    public static final int LAST_FRAME = 10;
    public static final int START_FRAME = 1;

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

    public Stream<Frame> stream() {
        return frames.stream();
    }

    public static List<Frame> framesByFirst(Frame firstFrame) {
        List<Frame> frames = new ArrayList<>();
        Frame temp = firstFrame;
        do {
            frames.add(temp);
            temp = temp.getNextFrame();
        } while (temp != null);
        return frames;
    }

    public static Stream<Frame> preview(Frame firstFrame) {
        List<Frame> frames = framesByFirst(firstFrame);
        int size = frames.size();
        return IntStream.range(0, Frames.LAST_FRAME)
                        .mapToObj(index -> index < size ? frames.get(index) : null);
    }
}