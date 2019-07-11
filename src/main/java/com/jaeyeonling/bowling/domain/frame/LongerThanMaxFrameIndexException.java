package com.jaeyeonling.bowling.domain.frame;

public class LongerThanMaxFrameIndexException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "프레임의 인덱스는 %d 보다 클 수 없습니다. (입력 값: %d)";

    LongerThanMaxFrameIndexException(final int input) {
        super(String.format(ERROR_MESSAGE, FrameIndex.MAX, input));
    }
}
