package bowling.step2.exception;

import bowling.step2.domain.Score;

public class ScoreRangeException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = String.format(
        "스코어는 %d ~ %d 사이의 값으로 입력해주세요",
        Score.MIN_SCORE,
        Score.MAX_SCORE
    );

    public ScoreRangeException() {
        super(ERROR_MESSAGE);
    }
}