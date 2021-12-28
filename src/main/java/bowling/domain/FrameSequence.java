package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import bowling.engine.Sequence;

public class FrameSequence implements Sequence {
    private static final Map<Integer, FrameSequence> cache = new HashMap<>();
    static {
        IntStream.rangeClosed(FIRST, LAST)
                .forEach(FrameSequence::initElement);
    }
    private static void initElement(int index) {
        cache.put(index, new FrameSequence(index));
    }

    public static final Sequence FIRST_FRAME = cache.get(FIRST);
    public static final Sequence FINAL_FRAME = cache.get(LAST);
    private static final int NEXT = 1;

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
    public Sequence next() {
        if (frame == LAST) {
            throw new IllegalStateException("the last frame does not have next..");
        }

        return of(frame + NEXT);
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
