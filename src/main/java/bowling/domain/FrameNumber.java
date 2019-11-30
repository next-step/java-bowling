package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FrameNumber {
    public static final int LAST_FRAME = 10;
    public static final int FIRST_FRAME = 1;
    public static final String FRAME_NUMBER_BOUND_ERROR_MESSAGE = "프레임 번호는 1이상 10이하 까지만 가능 합니다.";
    private static final Map<Integer, FrameNumber> cache;
    private int number;

    static {
        cache = new HashMap<>();
        for (int i = 0; i < LAST_FRAME; i++) {
            int number = i + 1;
            cache.put(number, new FrameNumber(number));
        }
    }

    public FrameNumber(int frameNumber) {
        this.number = frameNumber;
    }

    public static FrameNumber of(int frameNumber) {
        if (frameNumber < FIRST_FRAME || LAST_FRAME < frameNumber) {
            throw new IllegalArgumentException(FRAME_NUMBER_BOUND_ERROR_MESSAGE);
        }

        return cache.get(frameNumber);
    }

    public boolean isLastFrame() {
        return number == LAST_FRAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
