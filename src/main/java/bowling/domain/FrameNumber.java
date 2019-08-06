package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-17 00:36
 */
public class FrameNumber {
    public static final int NORMAL_FRAME_MAX_NUMBER = 9;
    private static final int FIRST_INDEX = 0;
    private static final int INIT_FRAME_NUMBER = 1;
    private static final int LAST_FRAME_NUMBER = 10;
    private static final List<FrameNumber> frameNumbers = IntStream.rangeClosed(INIT_FRAME_NUMBER, LAST_FRAME_NUMBER)
            .mapToObj(integer -> new FrameNumber(integer))
            .collect(Collectors.toList());

    private final int frameNumber;

    public FrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public static FrameNumber init() {
        return frameNumbers.get(FIRST_INDEX);
    }

    public int next() {
        if (frameNumber >= LAST_FRAME_NUMBER) {
            throw new IllegalStateException("10프레임까지만 게임할 수 있습니다.");
        }
        return frameNumbers.get(frameNumber).getFrameNumber();
    }

    public boolean isOver() {
        return frameNumber == LAST_FRAME_NUMBER;
    }

    public boolean isNormalFrameOver() {
        return frameNumber == NORMAL_FRAME_MAX_NUMBER;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public String toString() {
        return "FrameNumber{" +
                "frameNumber=" + frameNumber +
                '}';
    }
}
