package com.jaeyeonling.bowling.domain.frame;

import com.jaeyeonling.bowling.view.StringVisualizable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FrameIndex implements StringVisualizable {

    private static final Map<Integer, FrameIndex> POOL = new HashMap<>();

    static final int INCREMENT_VALUE = 1;
    static final int MAX = 10;
    static final int MIN = 1;

    private final int frameIndex;

    private FrameIndex(final int frameIndex) {
        this.frameIndex = frameIndex;
    }

    public static FrameIndex valueOf(final int frameIndex) {
        if (frameIndex < MIN) {
            throw new ShorterThanMinFrameIndexException(frameIndex);
        }
        if (frameIndex > MAX) {
            throw new LongerThanMaxFrameIndexException(frameIndex);
        }

        return POOL.computeIfAbsent(frameIndex, FrameIndex::new);
    }

    public static FrameIndex first() {
        return valueOf(MIN);
    }

    public static FrameIndex last() {
        return valueOf(MAX);
    }

    public FrameIndex next() {
        return valueOf(frameIndex + INCREMENT_VALUE);
    }

    boolean isMax() {
        return frameIndex == MAX;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FrameIndex)) {
            return false;
        }

        final FrameIndex that = (FrameIndex) o;
        return frameIndex == that.frameIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameIndex);
    }

    @Override
    public String visualize() {
        return String.valueOf(frameIndex);
    }
}
