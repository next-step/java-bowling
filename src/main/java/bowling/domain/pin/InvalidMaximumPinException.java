package bowling.domain.pin;

public class InvalidMaximumPinException extends RuntimeException {
    private static final String MESSAGE = "볼링 핀은 최대 10을 넘을 수 없습니다. 현재 쓰러진 핀 수는 %d";

    public InvalidMaximumPinException(int falledPins) {
        super(String.format(MESSAGE, falledPins));
    }
}
