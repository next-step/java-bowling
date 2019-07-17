package com.jaeyeonling.bowling.domain.pins;

public class LongerThanMaxKnockdownPinsException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "쓰러트린 핀의 갯수는 %d 보다 클 수 없습니다. (입력 값: %d)";

    LongerThanMaxKnockdownPinsException(final int input) {
        super(String.format(ERROR_MESSAGE, KnockdownPins.MAX_VALUE, input));
    }
}
