package bowling.domain;

import java.util.Objects;

public class FrameNo implements Comparable<FrameNo> {

    public static final int LAST_FRAME_NO = 10;
    private static final int INITIAL_FRAME_NO = 1;
    private static final int NEXT_FRAME_NO = 1;

    private final int value;

    private FrameNo(int value) {
        this.value = value;
    }

    public static FrameNo initialize() {
        return new FrameNo(INITIAL_FRAME_NO);
    }

    public FrameNo next() {
        return new FrameNo(nextFrameNo());
    }

    private int nextFrameNo() {
        return value + NEXT_FRAME_NO;
    }

    public boolean isNextFrameNoLast() {
        return nextFrameNo() == LAST_FRAME_NO;
    }

    public int toInt() {
        return value;
    }

    @Override
    public int compareTo(FrameNo o) {
        return Integer.compare(this.toInt(), o.toInt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNo frameNo = (FrameNo) o;
        return this.value == frameNo.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
