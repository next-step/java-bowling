package com.jaeyeonling.bowling.domain.count;

public class ShorterThanMinCountException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "카운트의 숫자는는 %d 보다 작을 수 없습니다. (입력 값: %d)";

    ShorterThanMinCountException(final int input) {
        super(String.format(ERROR_MESSAGE, Count.MIN, input));
    }
}