package bowling.exception;

import bowling.domain.Score;

public class InvalidScoreException extends IllegalArgumentException {

    private static final String MESSAGE = "기본 점수는 %d ~ %d 범위에 포함되어야 합니다. 입력값: %d";

    public InvalidScoreException(int score) {
        super(String.format(MESSAGE, Score.MIN_BASE_SCORE, Score.MAX_BASE_SCORE, score));
    }
}
