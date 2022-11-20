package bowling.domain.exception;

public class InvalidCountOfPinException extends RuntimeException {

    public InvalidCountOfPinException() {
        super("볼링핀은 음수가 될 수 없으며 10개까지 존재합니다.");
    }

}
