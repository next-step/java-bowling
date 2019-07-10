package com.jaeyeonling.bowling.frame;

public class ShorterThanMinFrameIndexException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "프레임의 인덱스는 %d 보다 작을 수 없습니다. (입력 값: %d)";

    ShorterThanMinFrameIndexException(final int input) {
        super(String.format(ERROR_MESSAGE, FrameIndex.MIN, input));
    }
}
