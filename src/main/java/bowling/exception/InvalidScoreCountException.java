package bowling.exception;

import bowling.domain.Score;

public class InvalidScoreCountException extends IllegalArgumentException {

    private static final String MESSAGE = "추가 점수 횟수는 %d ~ %d 범위에 포함되어야 합니다. 입력값: %d";

    public InvalidScoreCountException(int count) {
        super(String.format(MESSAGE, Score.MIN_ADDITIONAL_COUNT, Score.MAX_ADDITIONAL_COUNT, count));
    }
}
