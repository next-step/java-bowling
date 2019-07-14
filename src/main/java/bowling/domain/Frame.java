package bowling.domain;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-13 17:16
 */
public abstract class Frame {
    private static int AUTO_INCREASE = 1;
    private final int index;

    Frame() {
        this.index = AUTO_INCREASE++;
    }

    int getIndex() {
        return index;
    }

    abstract Frame bowl(int downCount);

    abstract boolean isGameOver();
}
