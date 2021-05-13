package bowling.exception;

public final class InvalidNameSizeException extends RuntimeException {

    private final String MESSAGE = "알맞는 이름의 크기가 아닙니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
