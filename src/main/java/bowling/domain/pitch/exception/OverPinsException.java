package bowling.domain.pitch.exception;

public class OverPinsException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "현재 남은 볼링 핀은 %s 개 입니다. 다시 확인해주세요.";

    public OverPinsException(int remainingPins) {
        super(String.format(EXCEPTION_MESSAGE, remainingPins));
    }
}
