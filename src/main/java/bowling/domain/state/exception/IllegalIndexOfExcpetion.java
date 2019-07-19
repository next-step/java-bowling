package bowling.domain.state.exception;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 14:11
 */
public class IllegalIndexOfExcpetion extends RuntimeException {

    public IllegalIndexOfExcpetion() {
        super("현재 INDEX는 데이터가 없습니다.");
    }
}
