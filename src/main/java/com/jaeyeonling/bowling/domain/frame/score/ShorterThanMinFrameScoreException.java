package com.jaeyeonling.bowling.domain.frame.score;

class ShorterThanMinFrameScoreException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "프레임의 점수는 %d 보다 작을 수 없습니다. (입력 값: %d)";

    ShorterThanMinFrameScoreException(final int input) {
        super(String.format(ERROR_MESSAGE, FrameScore.MIN, input));
    }
}
