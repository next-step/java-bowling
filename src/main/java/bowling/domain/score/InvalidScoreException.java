package bowling.domain.score;

public class InvalidScoreException extends RuntimeException {
    private static final String MESSAGE = "점수는 한번에 1에서 10사이여야 합니다.";

    public InvalidScoreException() {
        super(MESSAGE);
    }
}
