package bowling.exception;

public class ExceedFallenPinsException extends RuntimeException {

    public ExceedFallenPinsException() {
        super("쓰러뜨릴 수 있는 볼링핀 수를 초과하였습니다.");
    }

}
