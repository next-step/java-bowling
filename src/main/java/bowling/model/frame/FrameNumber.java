package bowling.model.frame;

import bowling.utility.Assert;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class FrameNumber {

    private static final int MINIMUM = 1;
    private static final int MAXIMUM = 10;
    private static final int INCREASE_STEP = 1;
    private static final Map<Integer, FrameNumber> CACHE = IntStream.rangeClosed(MINIMUM, MAXIMUM)
            .mapToObj(FrameNumber::new)
            .collect(Collectors.toMap(frameNumber -> frameNumber.number, frameNumber -> frameNumber));

    public static final FrameNumber FIRST = from(MINIMUM);
    public static final FrameNumber LAST = from(MAXIMUM);

    private final int number;

    private FrameNumber(int number) {
        Assert.isFalse(isLessThanMin(number) || isGreaterThanMax(number), String.format("number(%d) must be between %d and %d", number, MINIMUM, MAXIMUM));
        this.number = number;
    }

    public static FrameNumber from(int number) {
        return CACHE.getOrDefault(number, new FrameNumber(number));
    }

    FrameNumber increase() {
        if (isLast()) {
            throw new IllegalStateException(String.format("frame number(%s) can be increased up to %d", this, MAXIMUM));
        }
        return from(number + INCREASE_STEP);
    }

    boolean isLast() {
        return number == MAXIMUM;
    }

    public int toInt() {
        return number;
    }

    private boolean isGreaterThanMax(int number) {
        return MAXIMUM < number;
    }

    private boolean isLessThanMin(int number) {
        return number < MINIMUM;
    }

    @Override
    public String toString() {
        return "FrameNumber{" +
                "number=" + number +
                '}';
    }
}
