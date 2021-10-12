package bowling.domain.frame;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrameNumber {
    public static final int START_NUMBER = 1;

    public static final int FINAL_NUMBER = 10;

    private static final Map<Integer, FrameNumber> frameNumberMap =
            Collections.unmodifiableMap(IntStream.rangeClosed(START_NUMBER, FINAL_NUMBER)
                    .boxed()
                    .collect(Collectors.toMap(Function.identity(), FrameNumber::new)));

    private final int frameNumber;

    private FrameNumber(final int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FrameNumber of(final int inputFrameNumber) {
        FrameNumber frameNumber = frameNumberMap.get(inputFrameNumber);
        if (Objects.isNull(frameNumber)) {
            throw new IllegalArgumentException("프레임 번호는 1~10 사이 값이어야합니다.");
        }
        return frameNumber;
    }

    public FrameNumber next() {
        return of(frameNumber + 1);
    }

    public boolean isFinalNumber() {
        return Objects.equals(frameNumber, FINAL_NUMBER);
    }

    public int getFrameNumber() {
        return frameNumber;
    }
}
