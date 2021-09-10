package bowling.exception;

public class CannotAddPinsException extends RuntimeException {

    private static final String MESSAGE = "쓰러뜨린 볼링 핀은 최대 10개까지만 가능합니다.%n(현재까지 쓰러뜨린 볼링 핀 수: %d, 추가하려는 볼링 핀 수: %d)";

    public CannotAddPinsException(int currentPins, int fallenPins) {
        super(String.format(MESSAGE, currentPins, fallenPins));
    }

}
