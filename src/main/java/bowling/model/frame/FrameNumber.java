package bowling.model.frame;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameNumber implements Comparable<FrameNumber> {
    private static final String FRAME_NUMBER_ERROR = "범위를 벗어난 프레임 수 입니다.";
    private static final int PLACE_VALUE = 2;

    public static final int MAX_FRAME_NUMBER = 10;
    public static final int MIN_FRAME_NUMBER = 1;

    private static final Map<Integer, FrameNumber> frameNumberCache;

    private final int frameNumber;

    static {
        frameNumberCache = IntStream.rangeClosed(MIN_FRAME_NUMBER, MAX_FRAME_NUMBER)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), FrameNumber::new));
    }

    private FrameNumber(int number) {
        this.frameNumber = number;
    }

    public static FrameNumber from(int number) {
        if (frameNumberCache.containsKey(number)) {
            return frameNumberCache.get(number);
        }
        throw new IllegalArgumentException(FRAME_NUMBER_ERROR);
    }

    public FrameNumber next() {
        return FrameNumber.from(frameNumber + 1);
    }

    public boolean beforeLast() {
        return frameNumber == MAX_FRAME_NUMBER - 1;
    }

    @Override
    public String toString() {
        return String.format("%0" + PLACE_VALUE + "d", frameNumber);
    }

    @Override
    public int compareTo(FrameNumber o) {
        return Integer.compare(this.frameNumber, o.frameNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FrameNumber){
            return this.frameNumber == ((FrameNumber) obj).frameNumber;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
