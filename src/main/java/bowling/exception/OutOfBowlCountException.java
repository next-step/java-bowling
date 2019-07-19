package bowling.exception;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 23:12
 */
public class OutOfBowlCountException extends RuntimeException {
    public OutOfBowlCountException() {
        super("모든 게임이 종료되어 더이상 투구를 할 수 없습니다.");
    }
}
