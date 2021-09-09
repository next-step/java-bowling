package bowling.exception;

import bowling.domain.pin.Pin;

public final class PinOutOfBoundsException extends IllegalArgumentException {

    private static final String MESSAGE = "핀이 가질 수 있는 범의를 벗어났습니다 ( %d ~ %d ) ";

    public PinOutOfBoundsException() {
        super(String.format(MESSAGE, Pin.MIN ,Pin.MAX));
    }
}