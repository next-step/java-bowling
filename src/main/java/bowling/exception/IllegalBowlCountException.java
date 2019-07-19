package bowling.exception;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 13:58
 */
public class IllegalBowlCountException extends RuntimeException {

    public IllegalBowlCountException() {
        super("프레임 종료되었습니다.");
    }
}
