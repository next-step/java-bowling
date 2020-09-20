package bowling.exception;

public class ScoreMinAdditionalCountException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "추가 점수 갯수는 최소 0입니다.";

    public ScoreMinAdditionalCountException() {
        super(ERROR_MESSAGE);
    }

}
