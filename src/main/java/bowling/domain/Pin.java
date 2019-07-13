package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-13 13:22
 */
public class Pin {
    private static final int MAX_DOWN_COUNT = 10;
    private static final String EXCEPTION_INVALID_DOWN_COUNT_MESSAGE = "한번의 투구에 최대로 쓰러지는 Pin은 10개 입니다.";
    private final int downCount;

    public Pin(int downCount) {
        this.downCount = downCount;
    }

    public static Pin fallDown(int downCount) {
        if (downCount > MAX_DOWN_COUNT) {
            throw new IllegalArgumentException(EXCEPTION_INVALID_DOWN_COUNT_MESSAGE);
        }
        return new Pin(downCount);
    }

    public int fallCount() {
        return downCount;
    }
}
