package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import bowling.engin.Sequence;

public class FrameSequence implements Sequence {
    private static final int FIRST = 1;
    private static final int LAST = 10;
    private static final Map<Integer, FrameSequence> cache = new HashMap<>();
    static {
        IntStream.rangeClosed(FIRST, LAST)
                .forEach(FrameSequence::initElement);
    }
    private static void initElement(int index) {
        cache.put(index, new FrameSequence(index));
    }

    public static final Sequence FINAL = cache.get(LAST);

    private final int frame;

    private FrameSequence(int frame) {
        this.frame = frame;
    }

    public static FrameSequence of(int frame) {
        if (frame < FIRST || frame > LAST) {
            throw new IllegalArgumentException("frame must be 1 to 10");
        }

        return cache.get(frame);
    }

    @Override
    public int toInt() {
        return frame;
    }

    @Override
    public boolean isFinal() {
        return frame == LAST;
    }

    @Override
    public String toString() {
        return String.valueOf(frame);
    }
}
