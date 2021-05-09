package bowling.exception;

public final class InvalidScoreSizeException extends RuntimeException {

    private final String MESSAGE = "Score 범위를 벗어난 값이 입력 되었습니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
