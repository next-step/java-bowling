package bowling.exception;

public class InvalidScoreException extends RuntimeException {

    private static final String INVALID_SCORE_MESSAGE_FORMAT = "유효하지 않는 스코어 입니다 : %d";

    public InvalidScoreException(int score) {
        super(String.format(INVALID_SCORE_MESSAGE_FORMAT, score));
    }

}
