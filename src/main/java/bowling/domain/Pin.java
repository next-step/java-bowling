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
    private final int downCount;

    public Pin(int downCount) {
        this.downCount = downCount;
    }

    public static Pin fallDown(int downCount) {
        return new Pin(downCount);
    }

    public int fallCount() {
        return downCount;
    }
}
