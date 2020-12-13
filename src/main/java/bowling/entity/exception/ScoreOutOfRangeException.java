package bowling.entity.exception;

public class ScoreOutOfRangeException extends RuntimeException{

    private static final String MESSAGE = "점수는 0 이상의 값이어야 합니다. (score = %d)";

    public ScoreOutOfRangeException(int value) {
        super(String.format(MESSAGE, value));
    }
}
