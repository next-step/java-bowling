package bowling.exception;

public class NotFoundScoreException extends IllegalArgumentException {

    private static final String MESSAGE = "%d 값을 가진 점수는 없습니다.";

    public NotFoundScoreException(int totalCount) {
        super(String.format(MESSAGE, totalCount));
    }
}
