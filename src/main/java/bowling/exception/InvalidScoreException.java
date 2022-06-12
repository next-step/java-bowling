package bowling.exception;

import bowling.domain.Score;

public class InvalidScoreException extends IllegalArgumentException {

    private static final String MESSAGE = "점수의 최솟값은 %d 입니다. 입력값: %d";

    public InvalidScoreException(int score) {
        super(String.format(MESSAGE, Score.MIN_SCORE, score));
    }
}
