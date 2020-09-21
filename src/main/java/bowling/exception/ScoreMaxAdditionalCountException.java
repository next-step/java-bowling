package bowling.exception;

public class ScoreMaxAdditionalCountException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "추가 점수 갯수는 최대 2입니다.";

    public ScoreMaxAdditionalCountException() {
        super(ERROR_MESSAGE);
    }

}
