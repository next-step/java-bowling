package bowling.domain.pin;

public class InvalidMinimumPinException extends RuntimeException {
    private static final String MESSAGE = "볼링 핀은 최초 0 미만이 될 수 없습니다. 현재 쓰러진 핀 수는 %d";

    public InvalidMinimumPinException(int falledPins) {
        super(String.format(MESSAGE, falledPins));
    }
}
