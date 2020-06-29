package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class FrameNumber {

    private static final int MIN_FRAME_NUMBER = 1;
    private static final int MAX_FRAME_NUMBER = 10;

    private static final Map<Integer, FrameNumber> frameNumbers = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_FRAME_NUMBER, MAX_FRAME_NUMBER).forEach(frameNumber -> frameNumbers.put(frameNumber, new FrameNumber(frameNumber)));
    }

    private int frameNumber;

    private FrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FrameNumber of(int frameNumber) {
        return Optional.ofNullable(frameNumbers.get(frameNumber))
                .orElseThrow(() -> new IllegalArgumentException("프레임 번호의 범위를 넘었습니다."));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FrameNumber that = (FrameNumber) o;
        return frameNumber == that.frameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameNumber);
    }
}
