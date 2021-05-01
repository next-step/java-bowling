package bowling.domain.engine.frame;

import java.util.Objects;
import java.util.stream.IntStream;

public class FrameNumber {

    private static final int MIN = 1;
    private static final int MAX = 10;

    private static final FrameNumber[] CACHE = IntStream.rangeClosed(MIN, MAX)
                                                        .mapToObj(FrameNumber::new)
                                                        .toArray(FrameNumber[]::new);

    private final int value;

    private FrameNumber(int value) {
        this.value = value;
    }

    public static FrameNumber wrap(int value) {
        validate(value);
        return CACHE[value - 1];
    }

    private static void validate(int value) {
        if (value < MIN || MAX < value) {
            throw new IllegalArgumentException("프레임 번호는 1부터 10까지만 허용됩니다.");
        }
    }

    public static FrameNumber firstFrame() {
        return CACHE[MIN - 1];
    }

    public static FrameNumber finalFrame() {
        return CACHE[MAX - 1];
    }

    public int getNumber() {
        return value;
    }

    public FrameNumber getNextNumber() {
        try {
            return CACHE[value];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalStateException("마지막 프레임 번호에 다다랐기 때문에 다음 프레임 번호를 생성할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameNumber that = (FrameNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
